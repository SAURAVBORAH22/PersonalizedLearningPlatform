package com.example.PersonalizedLearningPlatform.chatBot.controller;

import com.example.PersonalizedLearningPlatform.chatBot.service.ChatBotService;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.PersonalizedLearningPlatform.common.constant.CommonConstants.PATH_VARIABLE_USER_ID;

@Slf4j
@CrossOrigin
@RequestMapping("/v1")
@RestController
public class ChatBotController {

  @Autowired
  @Qualifier("com.example.PersonalizedLearningPlatform.chatBot.service.impl.ChatBotServiceImpl")
  private ChatBotService chatBotService;

  @PostMapping("/user/{userId}/bot/chat")
  public ResponseEntity<PlpServiceResponseObject<String>> saveUserDetails(
      @PathVariable(PATH_VARIABLE_USER_ID) String userId, @RequestBody String userMessage) {
    log.info("Fetching chat response for userId:{} and userMessage:{}", userId, userMessage);
    PlpServiceResponseObject<String> response = chatBotService.getChatResponse(userMessage);
    return new ResponseEntity<>(response, response.getStatusCode());
  }
}
