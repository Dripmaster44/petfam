package com.petfam.petfam.socket.stomp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {

  private String content;
  private String sender;
  private MessageType type;

  @Override
  public String toString() {
    return "ChatMessage{" + "content=" + content + ", sender=" + sender + ", type=" + type + "}";
  }

  public enum MessageType {
    CHAT,
    JOIN,
    LEAVE
  }
}
