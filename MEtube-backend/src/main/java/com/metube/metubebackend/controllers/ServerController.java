package com.metube.metubebackend.controllers;

import com.metube.metubebackend.controllers.contracts.ServerCreateRequest;
import com.metube.metubebackend.entities.Server;
import com.metube.metubebackend.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServerController {

    @Autowired
    ServerService serverService;

    @GetMapping("/servers")
    public List<Server> getAllServers(){
        return serverService.getAll();
    }
    @GetMapping("/servers/{userId}")
    public List<Server> getServersOfUser(@PathVariable String userId){
        return serverService.getServersOfUser(userId);
    }

    @PostMapping("/servers/{userId}")
    public Server createServer(@PathVariable String userId, @RequestBody ServerCreateRequest server){
        return serverService.createServer(userId, server);
    }

    @PutMapping("/servers/{userId}")
    public Server updateServer(@PathVariable String userId, @RequestBody ServerCreateRequest server){
        return new Server();
    }

    @DeleteMapping("/servers/{userId}")
    public void deleteServers(@PathVariable String userId){
        serverService.deleteServers(userId);
    }
}
