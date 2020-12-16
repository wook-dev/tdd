package com.example.tdd.controller.param;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BbsAddParam {

  private String writer;
  private String title;
  private String content;

  @Builder
  public BbsAddParam(final String writer, final String title, final String content) {
    this.writer = writer;
    this.title = title;
    this.content = content;
  }
}
