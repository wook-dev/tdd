package com.example.tdd.bbs.service;

import static org.junit.Assert.assertEquals;

import com.example.tdd.bbs.model.BbsDto;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.controller.BbsController.BbsListParam;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsServiceTest {

  @Autowired
  BbsService bbsService;

  List<BbsEntity> bbsEntities;

  @Before
  public void setUp() {
    bbsEntities = IntStream.range(0, 20)
        .mapToObj(i ->
            bbsService.add(
                BbsDto
                    .builder()
                    .writer("wook")
                    .title("제목")
                    .content("내용")
                    .createTime(LocalDateTime.now())
                    .build()
            )
        )
        .collect(Collectors.toList());
  }

  @Test
  public void list() {
    //given
    final int page = 1;
    final int size = 10;
    final BbsListParam param = BbsListParam.builder().page(page).size(size).build();

    //when
    final Page<BbsEntity> entityPage = bbsService.list(param);

    //then
    assertEquals(bbsEntities.size(), entityPage.getTotalElements());
    assertEquals(
        bbsEntities
            .stream()
            .limit(size)
            .map(BbsEntity::getId)
            .collect(Collectors.toList()),
        entityPage.getContent()
            .stream()
            .map(BbsEntity::getId)
            .collect(Collectors.toList())
    );

    System.out.println("entityPage.getTotalElements() = " + entityPage.getTotalElements());
    System.out.println("entityPage.getContent() = " + entityPage.getContent());
  }

  @Test
  public void detail() {
    //given
    final long id = 7;

    //when
    final BbsEntity bbsEntity = bbsService.detail(id)
        .orElseThrow(() -> new RuntimeException("해당 글이 없습니다."));

    //then
    assertEquals(id, bbsEntity.getId());
    System.out.println("bbsEntity = " + bbsEntity);
  }

  @Test
  public void add() {
    //given
    //when
    //then
  }

  @Test
  public void edit() {
    //given
    //when
    //then
  }

  @Test
  public void delete() {
    //given
    //when
    //then
  }
}