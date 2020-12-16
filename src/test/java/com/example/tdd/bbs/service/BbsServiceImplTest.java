package com.example.tdd.bbs.service;

import static org.junit.Assert.assertEquals;

import com.example.tdd.bbs.model.BbsConverter;
import com.example.tdd.bbs.model.BbsDto;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.repository.BbsRepository;
import com.example.tdd.controller.BbsController.BbsListParam;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsServiceImplTest {

  @Autowired
  BbsRepository bbsRepository;

  List<BbsEntity> bbsEntities;

  @Before
  public void setUp() {
    bbsEntities = IntStream.range(0, 20)
        .mapToObj(i -> BbsDto
            .addBuilder()
            .writer("wook")
            .title("제목")
            .content("내용")
            .createTime(LocalDateTime.now())
            .build())
        .map(BbsConverter::convertToAdd)
        .collect(Collectors.toList());

    bbsRepository.save(bbsEntities);
  }

  @Test
  public void list() {
    //given
    final int page = 1;
    final int size = 10;
    final BbsListParam bbsListParam = BbsListParam
        .builder()
        .page(page)
        .size(size)
        .build();

    //when
    final Page<BbsEntity> entityPage = bbsRepository
        .findAll(new PageRequest(bbsListParam.getPage() - 1, bbsListParam.getSize()));

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

    System.out.println("entityPage.getContent() = " + entityPage.getContent());
    System.out.println("entityPage.getTotalElements() = " + entityPage.getTotalElements());
  }

  @Test
  public void detail() {
    //given
    final long id = 7;

    //when
    final BbsEntity bbsEntity = Optional.ofNullable(bbsRepository.findOne(id))
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
        .addBuilder()
        .writer(writer)
        .title(title)
        .content(content)
        .createTime(LocalDateTime.now())
        .build();

    //when
    final BbsEntity bbsEntity = bbsRepository.save(BbsConverter.convertToAdd(bbsDto));

    //then
    assertEquals(writer, bbsEntity.getWriter());
    assertEquals(title, bbsEntity.getTitle());
    assertEquals(content, bbsEntity.getContent());

    final BbsEntity detail = bbsRepository.findOne(bbsEntity.getId());
    assertEquals(bbsEntity.getId(), detail.getId());

    System.out.println("detail = " + detail);
  }

  @Test
  public void edit() {
  }

  @Test
  public void delete() {
  }
}