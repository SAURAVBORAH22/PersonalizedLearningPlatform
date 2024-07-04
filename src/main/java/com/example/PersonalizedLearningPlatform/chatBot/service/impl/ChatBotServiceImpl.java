package com.example.PersonalizedLearningPlatform.chatBot.service.impl;

import com.example.PersonalizedLearningPlatform.chatBot.service.ChatBotService;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.APPLICATION_JSON;
import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.AUTHORIZATION;
import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.BEARER;
import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.CONTENT_TYPE;
import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.OPEN_AI_CHAT_COMPLETION_FORMAT;
import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.OPEN_AI_CHAT_COMPLETION_URL;

@Slf4j
@Service("com.example.PersonalizedLearningPlatform.chatBot.service.impl.ChatBotServiceImpl")
public class ChatBotServiceImpl implements ChatBotService {

  @Value("${openai.api.key}")
  private String apiKey;

  private final HttpClient httpClient = HttpClient.newHttpClient();

  @Override
  public PlpServiceResponseObject<String> getChatResponse(String userMessage) {
    log.info("Fetch chat gpt response for a user message:{}", userMessage);
    PlpServiceResponseObject<String> response = new PlpServiceResponseObject<>();
    String requestBody = String.format(OPEN_AI_CHAT_COMPLETION_FORMAT, userMessage);
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(OPEN_AI_CHAT_COMPLETION_URL))
        .header(AUTHORIZATION, BEARER + apiKey).header(CONTENT_TYPE, APPLICATION_JSON)
        .POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
    try {
      HttpResponse<String> chatResponse =
          httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      response.setResponseObject(chatResponse.body());
      response.setStatus(PlpServiceStatusCode.SUCCESS.getCode());
    } catch (Exception e) {
      log.error("Exception:{} occurred while fetching chat response for a userMessage:{}", e,
          userMessage);
      response.setStatus(PlpServiceStatusCode.PROCESSING_ERROR.getCode());
    }
    return response;
  }
}
