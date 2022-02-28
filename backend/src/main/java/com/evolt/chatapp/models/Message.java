package com.evolt.chatapp.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String from;
    private String to;
    private String timestamp;
    private String content;
    private MessageType messageType;
}
