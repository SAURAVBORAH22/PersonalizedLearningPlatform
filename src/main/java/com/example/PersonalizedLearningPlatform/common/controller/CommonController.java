package com.example.PersonalizedLearningPlatform.common.controller;

import com.example.PersonalizedLearningPlatform.common.enums.Country;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.PATH_VARIABLE_USER_ID;

@Slf4j
@CrossOrigin
@RequestMapping("/v1")
@RestController
public class CommonController {

  @Autowired
  private CommonService commonService;

  @GetMapping("/user/{userId}/countries")
  public ResponseEntity<PlpServiceResponseObject<List<Country>>> getCountriesList(
      @PathVariable(PATH_VARIABLE_USER_ID) String userId) {
    log.info("Fetching countries for userId:{}", userId);
    PlpServiceResponseObject<List<Country>> response = commonService.getListOfCountries(userId);
    return new ResponseEntity<>(response, response.getStatusCode());
  }

  @GetMapping("/user/{userId}/roles")
  public ResponseEntity<PlpServiceResponseObject<List<String>>> getUserRoles(
      @PathVariable(PATH_VARIABLE_USER_ID) String userId) {
    log.info("Fetching user roles for userId:{}", userId);
    PlpServiceResponseObject<List<String>> response = commonService.getListOfUserRoles(userId);
    return new ResponseEntity<>(response, response.getStatusCode());
  }
}
