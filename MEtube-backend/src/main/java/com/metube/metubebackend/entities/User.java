package com.metube.metubebackend.entities;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="user")
public class User {

    @Id
    private String id;
    private String password; // Will be stored encrypted;
    private String username;
}
