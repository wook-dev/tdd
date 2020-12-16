package com.example.tdd.bbs.service;

import static org.junit.Assert.assertEquals;

import com.example.tdd.bbs.model.BbsConverter;
import com.example.tdd.bbs.model.BbsDto;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.repository.BbsRepository;
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
public class BbsServiceImplTest {

  @Autowired
  BbsService bbsService;

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
    final Page<BbsEntity> entityPage = bbsService.list(bbsListParam);

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
  }

  @Test
  public void add() {
  }

  @Test
  public void edit() {
  }

  @Test
  public void delete() {
  }
}