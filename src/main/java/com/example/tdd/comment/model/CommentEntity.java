package com.example.tdd.comment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class CommentEntity {

  @Id
  private long id;
}
