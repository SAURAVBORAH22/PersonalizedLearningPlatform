package com.example.PersonalizedLearningPlatform.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subject")
@NoArgsConstructor
@Getter
@Setter
public class Subject {
  @Id
  private String id;
  private String name;
}
