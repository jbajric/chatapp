package com.evolt.chatapp.api;

import com.evolt.chatapp.models.Message;
import com.evolt.chatapp.models.MessageType;
import com.evolt.chatapp.models.User;
import com.evolt.chatapp.services.UserServices;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserServices userServices;

    @SendTo("/topic/globalChat")
    @MessageMapping("/sendGlobalMessage")
    public Message send(@Payload Message message) {
        return message;
    }

    @GetMapping("/userJoined")
    public String userJoinedSystemMessage() {
        String generatedUsername = RandomStringUtils.randomAlphanumeric(10);
        Message message = Message.builder().from("SYSTEM_MSSG")
                .to(generatedUsername)
                .timestamp(Instant.now())
                .content(generatedUsername + " has joined the chat.")
                .messageType(MessageType.PARTICIPANT_JOINED).build();
        simpMessagingTemplate.convertAndSend("/topic/globalChat", message);
        userServices.addUser(new User(UUID.randomUUID(), generatedUsername));
        return generatedUsername;
    }

    @GetMapping("/userLeft")
    public void userLeftSystemMessage(@RequestParam String username) {
        Message message = Message.builder().from("SYSTEM_MSSG")
                .to(username)
                .timestamp(Instant.now())
                .content(username + " has left the chat.")
                .messageType(MessageType.PARTICIPANT_LEFT).build();
        simpMessagingTemplate.convertAndSend("/topic/globalChat", message);
        userServices.removeUser(username);
    }

    @GetMapping("/activeUsers")
    public List<String> getAllUsers() {
        return userServices.fetchAllUsers();
    }

}
