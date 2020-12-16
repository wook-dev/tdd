package com.example.tdd.bbs.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BbsDto {

  @NotEmpty
  private long id;

  @NotEmpty
  private String writer;

  @NotEmpty
  private String title;

  private String content;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  @Builder
  public BbsDto(final long id, final String writer, final String title, final String content,
      final LocalDateTime createTime, final LocalDateTime updateTime) {
    this.id = id;
    this.writer = writer;
    this.title = title;
    this.content = content;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
