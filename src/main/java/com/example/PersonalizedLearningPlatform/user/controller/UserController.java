package com.example.PersonalizedLearningPlatform.user.controller;

import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.user.model.UserModel;
import com.example.PersonalizedLearningPlatform.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.PATH_VARIABLE_USER_ID;

@Slf4j
@CrossOrigin
@RequestMapping("/v1")
@RestController
public class UserController {

  @Autowired
  @Qualifier("com.example.PersonalizedLearningPlatform.user.service.impl.UserServiceImpl")
  private UserService userService;

  @GetMapping("/user/{userId}/details")
  public ResponseEntity<PlpServiceResponseObject<UserModel>> getUserDetails(
      @PathVariable(PATH_VARIABLE_USER_ID) String userId) {
    log.info("Fetching user details for userId:{}", userId);
    PlpServiceResponseObject<UserModel> response = userService.getUserProfileDetails(userId);
    return new ResponseEntity<>(response, response.getStatusCode());
  }

  @PostMapping("/user/{userId}/details")
  public ResponseEntity<PlpServiceResponseObject<UserModel>> saveUserDetails(
      @PathVariable(PATH_VARIABLE_USER_ID) String userId, @RequestBody UserModel userModel) {
    log.info("Saving user profile details for userId:{} and userModel:{}", userId, userModel);
    PlpServiceResponseObject<UserModel> response =
        userService.saveUserProfileDetails(userId, userModel);
    return new ResponseEntity<>(response, response.getStatusCode());
  }
}

