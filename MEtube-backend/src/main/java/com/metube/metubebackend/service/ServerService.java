package com.metube.metubebackend.service;

import com.metube.metubebackend.controllers.contracts.ServerCreateRequest;
import com.metube.metubebackend.entities.Server;
import com.metube.metubebackend.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ServerService {
    @Autowired
    ServerRepository serverRepository;

    public List<Server> getAll(){
        return serverRepository.findAll();
    }

    public List<Server> getServersOfUser(String userId){
        return serverRepository.findAll().stream()
                .filter(server -> Objects.equals(server.getUserId(), userId))
                .collect(Collectors.toList());
    }

    public Server createServer(String userId, ServerCreateRequest server){
        Server newServer = server.toServer();
        newServer.setUserId(userId);
        serverRepository.save(newServer);
        return newServer;
    }

    public void deleteServers(String userId){
        List<Server> servers = serverRepository.findAll().stream()
                .filter(server -> Objects.equals(server.getUserId(), userId))
                .toList();
        for (Server server : servers){
            serverRepository.deleteById(server.getId());
        }
    }
}
