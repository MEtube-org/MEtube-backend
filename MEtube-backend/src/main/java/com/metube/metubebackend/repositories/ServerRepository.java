package com.metube.metubebackend.repositories;

import com.metube.metubebackend.entities.Server;
import com.metube.metubebackend.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepository extends MongoRepository<Server, String> {
    Optional<Server> findById(String id);
}
