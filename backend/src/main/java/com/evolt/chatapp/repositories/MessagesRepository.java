package com.evolt.chatapp.repositories;

import com.evolt.chatapp.models.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<MessageDTO, Integer> {

    @Override
    List<MessageDTO> findAll();
}
