package com.example.tdd.bbs.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BbsConverter {

  public static BbsEntity convertToBbsEntityForAdd(final BbsDto bbsDto) {
    return BbsEntity
        .builder()
        .writer(bbsDto.getWriter())
        .title(bbsDto.getTitle())
        .content(bbsDto.getContent())
        .createTime(bbsDto.getCreateTime())
        .build();
  }

  public static BbsEntity convertToBbsEntityForEdit(final BbsDto bbsDto) {
    return BbsEntity
        .builder()
        .id(bbsDto.getId())
        .title(bbsDto.getTitle())
        .content(bbsDto.getContent())
        .updateTime(bbsDto.getUpdateTime())
        .build();
  }
}
