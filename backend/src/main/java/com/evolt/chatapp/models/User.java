package com.evolt.chatapp.models;

import javax.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column (name = "username")
    @NotNull(message = "Username must be defined!")
    private String username;
}
