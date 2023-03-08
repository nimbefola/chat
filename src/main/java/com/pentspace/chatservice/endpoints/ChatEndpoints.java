package com.pentspace.chatservice.endpoints;

import com.pentspace.chatservice.entities.ChatMessage;
import com.pentspace.chatservice.entities.enums.MessageStatus;
import com.pentspace.chatservice.models.ChatNotification;
import com.pentspace.chatservice.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "chat")
public class ChatEndpoints {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        log.info(" Got incoming message [{}]", chatMessage);
        chatMessage.setStatus(MessageStatus.RECEIVED);
        ChatMessage saved = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderUsername()));
    }

    @GetMapping(path = "/sender/{senderId}", produces = "application/json")
    public ResponseEntity<List<ChatMessage>> getAllSenderChatMessage(@PathVariable("senderId") String senderId){
        return new ResponseEntity<>(chatMessageService.getBySenderId(senderId), HttpStatus.OK);
    }

    @GetMapping(path = "/sender/{senderId}/pending", produces = "application/json")
    public ResponseEntity<List<ChatMessage>> getAllUnReceivedUserChatMessage(@PathVariable("senderId") String senderId){
        return new ResponseEntity<>(chatMessageService.getBySenderIdAndStatus(senderId, MessageStatus.RECEIVED), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ChatMessage> getChatMessageById(@PathVariable("id") String id ){
        return new ResponseEntity<>(chatMessageService.getById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ChatMessage> markChatMessageAsDelivered(@PathVariable("id") String id ){
        return new ResponseEntity<>(chatMessageService.updateAsDelivered(id), HttpStatus.OK);
    }

}
