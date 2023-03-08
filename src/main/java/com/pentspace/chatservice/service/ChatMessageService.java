package com.pentspace.chatservice.service;

import com.pentspace.chatservice.entities.ChatMessage;
import com.pentspace.chatservice.entities.enums.MessageStatus;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> getBySenderId(String senderId);

    List<ChatMessage> getBySenderIdAndStatus(String senderId, MessageStatus status);
    ChatMessage getById(String id);
    ChatMessage updateAsDelivered(String id);
}
