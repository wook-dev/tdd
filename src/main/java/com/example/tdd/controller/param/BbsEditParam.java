package com.example.tdd.controller.param;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BbsEditParam {

  private String title;
  private String content;

  @Builder
  public BbsEditParam(final String title, final String content) {
    this.title = title;
    this.content = content;
  }
}
