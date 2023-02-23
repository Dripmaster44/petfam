package com.petfam.petfam.socket.stomp;

import com.petfam.petfam.jwt.JwtUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

public class AuthChannelInterceptor implements ChannelInterceptor {

  private final JwtUtil jwtUtil;

  public AuthChannelInterceptor(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);
    String destination = accessor.getDestination();
    if (destination != null && destination.startsWith("/topic")) {
      String token = accessor.getFirstNativeHeader("Authorization");
      if (token == null || !jwtUtil.validateToken(token)) {
        throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
      }
    } else if (destination != null && destination.equals("/app/chat/info")){
      String token = accessor.getFirstNativeHeader("Authorization");
      if (token == null == !jwtUtil.validateToken(token)) {
        throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
      }
      throw new IllegalArgumentException("권한이 없습니다.");
    }
    return message;
  }
}