package com.metube.metubebackend.entities;

import org.springframework.data.annotation.Id;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private long id;
    private String password; // Will be stored encrypted;
    private String username;
}
