package com.pentspace.chatservice.service.impl;

import com.pentspace.chatservice.entities.ChatMessage;
import com.pentspace.chatservice.entities.enums.MessageStatus;
import com.pentspace.chatservice.entities.repositories.ChatMessageRepository;
import com.pentspace.chatservice.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getBySenderId(String senderId) {
        return chatMessageRepository.findBySenderId(senderId);
    }

    @Override
    public List<ChatMessage> getBySenderIdAndStatus(String senderId, MessageStatus status) {
        return chatMessageRepository.findBySenderIdAndStatus(senderId, status);
    }

    @Override
    public ChatMessage getById(String id) {
        return chatMessageRepository.findById(id).orElseThrow(()->new NoSuchElementException("Chat not found"));
    }

    @Override
    public ChatMessage updateAsDelivered(String id) {
        ChatMessage chatMessage = getById(id);
        chatMessage.setStatus(MessageStatus.DELIVERED);
        return chatMessageRepository.save(chatMessage);
    }
}
