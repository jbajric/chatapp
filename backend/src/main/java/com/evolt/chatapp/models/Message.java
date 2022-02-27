package com.evolt.chatapp.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String from;
    private String to;
    private Instant timestamp;
    private String content;
    private MessageType messageType;
}
