package com.petfam.petfam.service;

import com.petfam.petfam.dto.MessageRequestDto;
import com.petfam.petfam.dto.MessageResponseDto;
import com.petfam.petfam.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface TalkService {
  public List<MessageResponseDto> sendMessage(Long receiveId, User user, MessageRequestDto messageRequestDto);
  public List<MessageResponseDto> getMessages(Long talkId, User user);

}
