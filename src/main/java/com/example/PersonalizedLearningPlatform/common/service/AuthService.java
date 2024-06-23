package com.example.PersonalizedLearningPlatform.common.service;

import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.user.entity.User;
import com.example.PersonalizedLearningPlatform.user.model.UserResponseModel;

public interface AuthService {

  /**
   * request to register user
   *
   * @param user
   *
   * @return
   */
  PlpServiceResponseObject<UserResponseModel> registerUser(User user);
}
