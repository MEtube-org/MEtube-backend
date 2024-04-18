package com.metube.metubebackend.controllers.contracts;

import com.metube.metubebackend.entities.Server;
import com.metube.metubebackend.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

@Getter
@AllArgsConstructor
public class ServerCreateRequest {
    private String ip;
    private String port;

    public Server toServer(){
        Server server = new Server();
        BeanUtils.copyProperties(this, server);
        return server;
    }

}
