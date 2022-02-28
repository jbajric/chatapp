package com.evolt.chatapp.api;

import com.evolt.chatapp.models.Message;
import com.evolt.chatapp.models.MessageDTO;
import com.evolt.chatapp.models.MessageType;
import com.evolt.chatapp.models.User;
import com.evolt.chatapp.services.MessageService;
import com.evolt.chatapp.services.UserServices;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "*")
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserServices userServices;

    @Autowired
    private MessageService messageService;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

    @SendTo("/topic/globalChat")
    @MessageMapping("/sendGlobalMessage")
    public Message send(@Payload Message message) {
        saveMessageToDatabase(message);
        return message;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/userJoined")
    public String userJoinedSystemMessage() {
        String generatedUsername = RandomStringUtils.randomAlphanumeric(10);
        Message message = Message.builder().from("SYSTEM_MSSG")
                .to(generatedUsername)
                .timestamp(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())))
                .content(generatedUsername + " has joined the chat.")
                .messageType(MessageType.PARTICIPANT_JOINED).build();
        simpMessagingTemplate.convertAndSend("/topic/globalChat", message);
        saveMessageToDatabase(message);
        userServices.addUser(new User(UUID.randomUUID(), generatedUsername));
        return generatedUsername;
    }

    @GetMapping("/userLeft")
    @CrossOrigin(origins = "*")
    public void userLeftSystemMessage(@RequestParam String username) {
        Message message = Message.builder().from("SYSTEM_MSSG")
                .to(username)
                .timestamp(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())))
                .content(username + " has left the chat.")
                .messageType(MessageType.PARTICIPANT_LEFT).build();
        simpMessagingTemplate.convertAndSend("/topic/globalChat", message);
        saveMessageToDatabase(message);
        userServices.removeUser(username);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/activeUsers")
    public List<String> getAllUsers() {
        return userServices.fetchAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allSentMessages")
    public List<MessageDTO> getAllSentMessages() {
        return messageService.fetchAllMessages();
    }

    private void saveMessageToDatabase(Message message) {
        MessageDTO messageDTO = MessageDTO.builder().id(UUID.randomUUID())
                .sender(message.getFrom())
                .sendingTimestamp(message.getTimestamp())
                .content(message.getContent()).build();
        messageService.addMessage(messageDTO);
    }
}
