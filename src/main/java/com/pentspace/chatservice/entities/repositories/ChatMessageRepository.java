package com.pentspace.chatservice.entities.repositories;

import com.pentspace.chatservice.entities.ChatMessage;
import com.pentspace.chatservice.entities.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    List<ChatMessage> findBySenderId(String senderId);

    List<ChatMessage> findBySenderIdAndStatus(String senderId, MessageStatus status);

}
