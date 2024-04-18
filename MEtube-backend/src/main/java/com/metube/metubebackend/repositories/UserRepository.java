package com.metube.metubebackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.metube.metubebackend.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findById(String id);
    Optional<UserEntity> findByUsername(String Username);
}
