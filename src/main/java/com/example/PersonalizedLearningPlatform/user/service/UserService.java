package com.example.PersonalizedLearningPlatform.user.service;

import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.user.model.UserModel;

public interface UserService {

  /**
   * get user profile details by userId
   *
   * @param userId
   *
   * @return
   */
  PlpServiceResponseObject<UserModel> getUserProfileDetails(String userId);

  /**
   * save user profile details for a user by userId
   *
   * @param userId
   * @param userModel
   *
   * @return
   */
  PlpServiceResponseObject<UserModel> saveUserProfileDetails(String userId, UserModel userModel);
}
