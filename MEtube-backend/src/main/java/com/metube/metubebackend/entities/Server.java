package com.metube.metubebackend.entities;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="server")
public class Server {

    @Id
    private String id;
    private String userId;
    private String ip;
    private String port;
}
