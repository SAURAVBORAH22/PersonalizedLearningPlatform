package com.example.PersonalizedLearningPlatform.user.repository;

import com.example.PersonalizedLearningPlatform.user.entity.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubjectRepository extends MongoRepository<Subject, String> {
  List<Subject> findByNameIn(List<String> names);
}
