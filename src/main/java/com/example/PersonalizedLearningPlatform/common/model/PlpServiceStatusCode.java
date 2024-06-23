package com.example.PersonalizedLearningPlatform.common.model;

public enum PlpServiceStatusCode {
  SUCCESS("Success"),
  DATA_VALIDATION_FAILED("Data validation failed"),
  USERNAME_OR_EMAIL_ALREADY_EXISTS("Username or email id already exists"),
  USER_DOES_NOT_EXISTS("User does not exists"),
  SUBJECT_CANNOT_BE_EMPTY("Subject cannot be empty"),
  PROCESSING_ERROR("There was an error processing your request");

  private final String code;

  PlpServiceStatusCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}