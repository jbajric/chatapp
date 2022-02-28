package com.evolt.chatapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class MessageDTO implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "sender")
    @NotNull(message = "Sender must be defined!")
    private String sender;

    @Column(name = "sendingTimestamp")
    @NotNull(message = "Timestamp must be defined!")
    private String sendingTimestamp;

    @Column(name = "content")
    @NotNull(message = "Content must be defined!")
    private String content;
}