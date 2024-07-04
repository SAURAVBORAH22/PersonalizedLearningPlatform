package com.example.PersonalizedLearningPlatform.chatBot.service;

import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;

public interface ChatBotService {

  /**
   * get chat gpt response for a userMessage
   *
   * @param userMessage
   *
   * @return
   */
  PlpServiceResponseObject<String> getChatResponse(String userMessage);
}
