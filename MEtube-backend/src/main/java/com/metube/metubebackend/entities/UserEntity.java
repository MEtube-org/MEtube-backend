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
public class UserEntity {

    @Id
    private String id;
    private String password;
    private String username;
    private String roles = "USER";
}
