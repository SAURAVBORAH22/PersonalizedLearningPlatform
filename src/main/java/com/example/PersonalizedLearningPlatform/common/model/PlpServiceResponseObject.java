package com.example.PersonalizedLearningPlatform.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@ToString(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PlpServiceResponseObject<T> {
  private T responseObject;
  private String status;

  @JsonIgnore
  private HttpStatus statusCode;

  public PlpServiceResponseObject() {
    this.statusCode = HttpStatus.OK;
  }

  public PlpServiceResponseObject(String status) {
    this();
    this.status = status;
  }

  public PlpServiceResponseObject(String status, T responseObject) {
    this(status);
    this.responseObject = responseObject;
  }
}
