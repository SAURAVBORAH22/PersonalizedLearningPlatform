package com.example.PersonalizedLearningPlatform.common.service.impl;

import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceStatusCode;
import com.example.PersonalizedLearningPlatform.common.service.AuthService;
import com.example.PersonalizedLearningPlatform.user.entity.User;
import com.example.PersonalizedLearningPlatform.user.model.UserResponseModel;
import com.example.PersonalizedLearningPlatform.user.repository.UserRepository;
import com.example.PersonalizedLearningPlatform.user.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service("com.example.PersonalizedLearningPlatform.common.service.impl.AuthServiceImpl")
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  public PlpServiceResponseObject<UserResponseModel> registerUser(User user) {
    log.info("Request to register user:{}", user);
    PlpServiceResponseObject<UserResponseModel> response =
        new PlpServiceResponseObject<>(PlpServiceStatusCode.PROCESSING_ERROR.getCode());
    if (Objects.nonNull(user.getUsername()) || Objects.nonNull(user.getEmailId())
        || Objects.nonNull(user.getPassword())) {
      Optional<User> userDb =
          userRepository.findByUsernameOrEmailId(user.getUsername(), user.getEmailId());
      if (userDb.isPresent()) {
        response.setStatus(PlpServiceStatusCode.USERNAME_OR_EMAIL_ALREADY_EXISTS.getCode());
      } else {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userDetailsService.save(user);
        response.setResponseObject(new UserResponseModel(user));
        response.setStatus(PlpServiceStatusCode.SUCCESS.getCode());
      }
    } else {
      response.setStatus(PlpServiceStatusCode.DATA_VALIDATION_FAILED.getCode());
    }
    return response;
  }
}
