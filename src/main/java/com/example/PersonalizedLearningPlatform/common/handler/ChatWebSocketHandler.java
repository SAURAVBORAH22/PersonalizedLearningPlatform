package com.example.PersonalizedLearningPlatform.common.handler;

import com.example.PersonalizedLearningPlatform.chatBot.service.ChatBotService;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

  private final ChatBotService chatBotService;

  public ChatWebSocketHandler(ChatBotService chatBotService) {
    this.chatBotService = chatBotService;
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String userMessage = message.getPayload();
    PlpServiceResponseObject<String> response = chatBotService.getChatResponse(userMessage);
    session.sendMessage(new TextMessage(response.getResponseObject()));
  }
}
