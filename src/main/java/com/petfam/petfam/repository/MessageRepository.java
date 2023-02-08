package com.petfam.petfam.repository;

import com.petfam.petfam.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
  List<Message> findAllByTalk(Long talkId);
}
