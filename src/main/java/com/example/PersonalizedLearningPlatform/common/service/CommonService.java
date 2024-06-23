package com.example.PersonalizedLearningPlatform.common.service;

import com.example.PersonalizedLearningPlatform.common.enums.Country;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;

import java.util.List;

public interface CommonService {

  /**
   * fetch list of countries
   *
   * @param userId
   *
   * @return
   */
  PlpServiceResponseObject<List<Country>> getListOfCountries(String userId);

  /**
   * fetch list of user roles
   *
   * @param userId
   *
   * @return
   */
  PlpServiceResponseObject<List<String>> getListOfUserRoles(String userId);
}
