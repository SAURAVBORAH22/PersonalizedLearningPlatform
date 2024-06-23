package com.example.PersonalizedLearningPlatform.user.repository;


import com.example.PersonalizedLearningPlatform.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
  User findByUsername(String username);

  Optional<User> findByUsernameOrEmailId(String username, String emailId);

  Optional<User> findById(String id);
}
