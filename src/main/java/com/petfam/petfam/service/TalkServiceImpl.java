package com.petfam.petfam.service;

import com.petfam.petfam.dto.MessageRequestDto;
import com.petfam.petfam.dto.MessageResponseDto;
import com.petfam.petfam.entity.Message;
import com.petfam.petfam.entity.Talk;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.repository.MessageRepository;
import com.petfam.petfam.repository.TalkRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TalkServiceImpl implements TalkService{

  private final TalkRepository talkRepository;
  private final MessageRepository messageRepository;

  @Override
  @Transactional
  public List<MessageResponseDto> sendMessage(Long receiveId, User user, MessageRequestDto messageRequestDto) {
    if(talkRepository.findByApplyIdAndReceiveId(user.getId(), receiveId).isEmpty()) {
      Talk talk = new Talk(user.getId(), receiveId);
      Message message = new Message(talk.getId(), user.getNickname(), messageRequestDto);
      messageRepository.save(message);
      return getMessages(talk.getId(),user);
    } else {
      Talk talk = _findTalk(receiveId,user.getId());
      Message message = new Message(talk.getId(), user.getNickname(), messageRequestDto);
      messageRepository.save(message);
      return getMessages(talk.getId(),user);
    }
  }
  @Override
  @Transactional(readOnly = true)
  public List<MessageResponseDto> getMessages(Long talkId, User user) {
    Talk talk = _findTalk(talkId);
    if ((!talk.getApplyId().equals(user.getId())) && (!talk.getReceiveId().equals(user.getId()))) {
      throw new IllegalArgumentException("해당 톡방에 접근권한이 없습니다.");
    }

    List<Message> messages = messageRepository.findAllByTalk(talkId);
    List<MessageResponseDto> messageResponseDtos = new ArrayList<>();
    for (Message message : messages) {
      messageResponseDtos.add(new MessageResponseDto(message));
    }
    return messageResponseDtos;
  }

  private Talk _findTalk(Long receiveId, Long userId) {
    Talk talk = talkRepository.findByApplyIdAndReceiveId(userId, receiveId).orElseThrow(
        () -> new IllegalArgumentException("톡방이 존재하지 않습니다.")
    );
    return talk;
  }

  private Talk _findTalk(Long talkId) {
    Talk talk = talkRepository.findById(talkId).orElseThrow(
        () -> new IllegalArgumentException("톡방이 존재하지 않습니다.")
    );
    return talk;
  }
}
