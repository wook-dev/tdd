package com.example.tdd.bbs.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class BbsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String writer;

  @Column
  private String title;

  @Column
  private String content;

  @Column(updatable = false)
  private LocalDateTime createTime;

  @Column
  private LocalDateTime updateTime;

  @Builder
  public BbsEntity(final String writer, final String title, final String content,
      final LocalDateTime createTime,
      final LocalDateTime updateTime) {
    this.writer = writer;
    this.title = title;
    this.content = content;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
