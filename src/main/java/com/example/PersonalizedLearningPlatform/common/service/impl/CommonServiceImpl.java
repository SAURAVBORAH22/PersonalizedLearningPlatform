package com.example.PersonalizedLearningPlatform.common.service.impl;

import com.example.PersonalizedLearningPlatform.common.constant.CommonConstants;
import com.example.PersonalizedLearningPlatform.common.enums.Country;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceStatusCode;
import com.example.PersonalizedLearningPlatform.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service("com.example.PersonalizedLearningPlatform.common.service.impl.CommonServiceImpl")
public class CommonServiceImpl implements CommonService {

  @Override
  public PlpServiceResponseObject<List<Country>> getListOfCountries(String userId) {
    log.info("Fetching countries list for user with userId:{}", userId);
    PlpServiceResponseObject<List<Country>> response = new PlpServiceResponseObject<>();
    List<Country> countries = Country.getAllCountries();
    if (CollectionUtils.isEmpty(countries)) {
      response.setStatus(PlpServiceStatusCode.PROCESSING_ERROR.getCode());
    } else {
      response.setResponseObject(countries);
      response.setStatus(PlpServiceStatusCode.SUCCESS.getCode());
    }
    return response;
  }

  @Override
  public PlpServiceResponseObject<List<String>> getListOfUserRoles(String userId) {
    log.info("Fetch list of user role for user with userId:{}", userId);
    PlpServiceResponseObject<List<String>> response =
        new PlpServiceResponseObject<>(PlpServiceStatusCode.PROCESSING_ERROR.getCode());
    List<String> userRoles = CommonConstants.USER_ROLE_LIST;
    if (!CollectionUtils.isEmpty(userRoles)) {
      response.setResponseObject(userRoles);
      response.setStatus(PlpServiceStatusCode.SUCCESS.getCode());
    }
    return response;
  }
}
