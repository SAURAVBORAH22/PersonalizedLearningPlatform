package com.example.PersonalizedLearningPlatform.user.model;

import com.example.PersonalizedLearningPlatform.user.entity.Subject;
import com.example.PersonalizedLearningPlatform.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@ToString(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
  private String id;
  private String username;
  private String emailId;
  private Timestamp dateOfBirth;
  private String country;
  private Long age;
  private String userRole;
  private List<Subject> subjects;
  private String learningGoals;

  public UserModel(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.emailId = user.getEmailId();
    this.dateOfBirth = user.getDateOfBirth();
    this.country = user.getCountry();
    this.age = user.getAge();
    this.userRole = user.getUserRole();
    this.learningGoals = user.getLearningGoals();
  }
}
