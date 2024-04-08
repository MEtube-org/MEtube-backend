package com.metube.metubebackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.metube.metubebackend.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<User, Long> {
    Optional<User> findById(Long id);
}
