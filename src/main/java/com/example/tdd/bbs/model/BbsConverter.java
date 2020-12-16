package com.example.tdd.bbs.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BbsConverter {

  public static BbsEntity convertToAdd(final BbsDto bbsDto) {
    return BbsEntity
        .builder()
        .writer(bbsDto.getWriter())
        .title(bbsDto.getTitle())
        .content(bbsDto.getContent())
        .createTime(bbsDto.getCreateTime())
        .build();
  }
}
