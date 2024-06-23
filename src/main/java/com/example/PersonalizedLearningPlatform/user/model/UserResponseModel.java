package com.example.PersonalizedLearningPlatform.user.model;

import com.example.PersonalizedLearningPlatform.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserResponseModel {
  private String id;
  private String userName;
  private String emailId;

  public UserResponseModel(User user){
    this.id = user.getId();
    this.userName = user.getUsername();
    this.emailId = user.getEmailId();
  }
}
