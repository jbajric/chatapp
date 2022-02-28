package com.evolt.chatapp.services;


import com.evolt.chatapp.models.MessageDTO;
import com.evolt.chatapp.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MessageService")
public class MessageService {
    @Autowired
    private MessagesRepository messagesRepository;

    public void addMessage(MessageDTO messageDTO) {
        messagesRepository.save(messageDTO);
    }

    public List<MessageDTO> fetchAllMessages() {
        return messagesRepository.findAll();
    }
}