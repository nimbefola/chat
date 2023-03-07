package com.pentspace.chatservice.service.impl;

import com.pentspace.chatservice.entities.ChatMessage;
import com.pentspace.chatservice.entities.repositories.ChatMessageRepository;
import com.pentspace.chatservice.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
}
