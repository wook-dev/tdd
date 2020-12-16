package com.example.tdd.bbs.model;

import com.example.tdd.controller.param.BbsAddParam;
import com.example.tdd.controller.param.BbsEditParam;
import java.time.LocalDateTime;
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

  public static BbsDto convertToBbsDtoForAdd(final BbsAddParam bbsAddParam) {
    return BbsDto
        .builder()
        .writer(bbsAddParam.getWriter())
        .title(bbsAddParam.getTitle())
        .content(bbsAddParam.getContent())
        .createTime(LocalDateTime.now())
        .build();
  }

  public static BbsDto convertToBbsDtoForEdit(final long id, final BbsEditParam bbsEditParam) {
    return BbsDto
        .builder()
        .id(id)
        .title(bbsEditParam.getTitle())
        .content(bbsEditParam.getContent())
        .updateTime(LocalDateTime.now())
        .build();
  }
}
