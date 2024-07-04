package com.example.PersonalizedLearningPlatform.common.constant;

import java.util.Arrays;
import java.util.List;

public interface CommonConstants {
  String PATH_VARIABLE_USER_ID = "userId";
  List<String> USER_ROLE_LIST = Arrays.asList("Student", "Tutor");
  String OPEN_AI_CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";
  String OPEN_AI_CHAT_COMPLETION_FORMAT =
      "{\"model\":\"gpt-4\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}]}";
  String AUTHORIZATION = "Authorization";
  String BEARER = "Bearer ";
  String CONTENT_TYPE = "Content-Type";
  String APPLICATION_JSON  = "application/json";
}
