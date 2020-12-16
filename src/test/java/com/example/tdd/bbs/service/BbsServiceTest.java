package com.example.tdd.bbs.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
    final String writer = "tester";
    final String title = "테스트 제목";
    final String content = "테스트 내용";

    final BbsDto bbsDto = BbsDto
        .builder()
        .writer(writer)
        .title(title)
        .content(content)
        .createTime(LocalDateTime.now())
        .build();

    //when
    final BbsEntity bbsEntity = bbsService.add(bbsDto);

    //then
    assertEquals(writer, bbsEntity.getWriter());
    assertEquals(title, bbsEntity.getTitle());
    assertEquals(content, bbsEntity.getContent());

    final BbsEntity detail = bbsService.detail(bbsEntity.getId())
        .orElseThrow(() -> new RuntimeException("해당 글이 없습니다."));

    assertEquals(bbsEntity.getId(), detail.getId());

    System.out.println("detail = " + detail);
  }

  @Test
  public void edit() {
    //given
    final long id = 7;
    final String title = "타이틀 변경";
    final String content = "내용 변경";
    final BbsDto bbsDto = BbsDto
        .builder()
        .id(id)
        .title(title)
        .content(content)
        .updateTime(LocalDateTime.now())
        .build();

    //when
    bbsService.edit(bbsDto);
    final BbsEntity editBbsEntity = bbsService.detail(id)
        .orElseThrow(() -> new RuntimeException("해당 글이 없습니다."));

    //then
    assertEquals(title, editBbsEntity.getTitle());
    assertEquals(content, editBbsEntity.getContent());

    System.out.println("editBbsEntity = " + editBbsEntity);
  }

  @Test
  public void delete() {
    //given
    final long id = 7;

    //when
    bbsService.delete(id);

    //then
    assertFalse(bbsService.detail(id).isPresent());
  }
}