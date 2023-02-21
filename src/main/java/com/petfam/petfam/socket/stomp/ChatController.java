package com.petfam.petfam.socket.stomp;

import com.petfam.petfam.jwt.JwtUtil;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.socket.stomp.ChatMessage.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@Controller
public class ChatController {
  private final JwtUtil jwtUtil;

  public ChatController(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @GetMapping("/chat/info")
  public ResponseEntity<Void> getInfo(@RequestHeader("Authorization") String token) {
    if (token == null || !jwtUtil.validateToken(token)) {
      throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @MessageMapping("/chat/sendmessage")
  @SendTo("/topic/public")
  public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    String sender = userDetails.getUsername();
    return new ChatMessage(chatMessage.getContent(), sender, MessageType.CHAT);
  }

  @MessageMapping("/chat/adduser")
  @SendTo("/topic/public")
  public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    headerAccessor.getSessionAttributes().put("username" , chatMessage.getSender());
    String sender = (String) headerAccessor.getSessionAttributes().get("username");
    return new ChatMessage(chatMessage.getContent(), sender, MessageType.JOIN);
  }

  @MessageMapping("/chat/leaveuser")
  @SendTo("/topic/public")
  public ChatMessage leaveUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    headerAccessor.getSessionAttributes().put("username" , chatMessage.getSender());
    String sender = (String) headerAccessor.getSessionAttributes().get("username");
    return new ChatMessage(chatMessage.getContent(), sender, MessageType.LEAVE);
  }

}
