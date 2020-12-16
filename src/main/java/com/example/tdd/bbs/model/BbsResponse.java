package com.example.tdd.bbs.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(doNotUseGetters = true, of = {"id"})
public class BbsResponse {

  private long id;
  private String writer;
  private String title;
  private String content;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  @Builder
  public BbsResponse(final long id, final String writer, final String title, final String content,
      final LocalDateTime createTime, final LocalDateTime updateTime) {
    this.id = id;
    this.writer = writer;
    this.title = title;
    this.content = content;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
