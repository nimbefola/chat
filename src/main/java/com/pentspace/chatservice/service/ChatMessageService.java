package com.pentspace.chatservice.service;

import com.pentspace.chatservice.entities.ChatMessage;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
}
