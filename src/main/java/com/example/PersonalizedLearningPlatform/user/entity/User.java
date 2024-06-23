package com.example.PersonalizedLearningPlatform.user.entity;

import com.example.PersonalizedLearningPlatform.user.model.UserModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;

@Document(collection = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

  @Id
  private String id;
  private String username;
  private String emailId;
  private String password;
  private Timestamp dateOfBirth;
  private String country;
  private Long age;
  private String userRole;
  private List<String> subjectName;
  private String learningGoals;

  public User(UserModel userModel) {
    this.id = userModel.getId();
    this.username = userModel.getUsername();
    this.emailId = userModel.getEmailId();
    this.dateOfBirth = userModel.getDateOfBirth();
    this.country = userModel.getCountry();
    this.age = userModel.getAge();
    this.userRole = userModel.getUserRole();
    this.learningGoals = userModel.getLearningGoals();
  }
}