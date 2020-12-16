package com.example.tdd.controller.param;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class BbsListParam {

  @Builder
  public BbsListParam(final int page, final int size) {
    this.page = page;
    this.size = size;
  }

  private int page;
  private int size;

  public int getPage() {
    return Math.max(page, 1);
  }

  public int getSize() {
    return Math.max(size, 10);
  }
}
