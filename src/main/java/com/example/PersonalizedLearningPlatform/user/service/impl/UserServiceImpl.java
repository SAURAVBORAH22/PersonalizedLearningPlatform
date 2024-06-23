package com.example.PersonalizedLearningPlatform.user.service.impl;

import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceStatusCode;
import com.example.PersonalizedLearningPlatform.user.entity.Subject;
import com.example.PersonalizedLearningPlatform.user.entity.User;
import com.example.PersonalizedLearningPlatform.user.model.UserModel;
import com.example.PersonalizedLearningPlatform.user.repository.SubjectRepository;
import com.example.PersonalizedLearningPlatform.user.repository.UserRepository;
import com.example.PersonalizedLearningPlatform.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service("com.example.PersonalizedLearningPlatform.user.service.impl.UserServiceImpl")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Override
  public PlpServiceResponseObject<UserModel> getUserProfileDetails(String userId) {
    log.info("Fetching user profile details for id:{}", userId);
    PlpServiceResponseObject<UserModel> response =
        new PlpServiceResponseObject<>(PlpServiceStatusCode.PROCESSING_ERROR.getCode());
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      UserModel userModel = new UserModel(userOptional.get());
      if (!CollectionUtils.isEmpty(userOptional.get().getSubjectName())) {
        List<Subject> subjects =
            subjectRepository.findByNameIn(userOptional.get().getSubjectName());
        userModel.setSubjects(subjects);
      }
      response.setResponseObject(userModel);
      response.setStatus(PlpServiceStatusCode.SUCCESS.getCode());
    } else {
      response.setStatus(PlpServiceStatusCode.USER_DOES_NOT_EXISTS.getCode());
    }
    return response;
  }

  @Override
  public PlpServiceResponseObject<UserModel> saveUserProfileDetails(String userId,
      UserModel userModel) {
    log.info("Fetching user profile details for userModel:{} and userId:{}", userModel, userId);
    PlpServiceResponseObject<UserModel> response =
        new PlpServiceResponseObject<>(PlpServiceStatusCode.PROCESSING_ERROR.getCode());
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      User user = new User(userModel);
      if (CollectionUtils.isEmpty(userModel.getSubjects())) {
        response.setStatus(PlpServiceStatusCode.SUBJECT_CANNOT_BE_EMPTY.getCode());
      } else {
        //saving subject to repository if new subject is added
        List<String> subjectNamesToInsert = userModel.getSubjects().stream().peek(subject -> {
          if (Objects.isNull(subject.getId())) {
            subjectRepository.save(subject);
          }
        }).map(Subject::getName).toList();
        user.setSubjectName(subjectNamesToInsert);
        userRepository.save(user);
        response.setResponseObject(userModel);
        response.setStatus(PlpServiceStatusCode.SUCCESS.getCode());
      }
    } else {
      response.setStatus(PlpServiceStatusCode.USER_DOES_NOT_EXISTS.getCode());
    }
    return response;
  }
}
