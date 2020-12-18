package com.example.tdd.bbs.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.repository.BbsRepository;
import com.example.tdd.controller.param.BbsListParam;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockTest {

  @Autowired
  BbsService bbsService;

  @MockBean
  BbsRepository bbsRepository;

  List<BbsEntity> bbsEntities;

  @Before
  public void setUp() {
    bbsEntities = IntStream.range(0, 10)
        .mapToObj(i -> BbsEntity
            .builder()
            .id(i + 1)
            .writer("작성자_" + (i + 1))
            .title("제목_" + (i + 1))
            .content("내용_" + (i + 1))
            .createTime(LocalDateTime.now())
            .build()
        )
        .collect(Collectors.toList());
  }

  @Test
  @Ignore
  public void list() {
    //given
    given(bbsRepository.findAll(new PageRequest(0, 10))).willReturn(new PageImpl<>(bbsEntities));

    //when
    final Page<BbsEntity> pages = bbsService.list(BbsListParam.builder().page(1).size(10).build());

    //then
    assertEquals(10, pages.getTotalElements());
    System.out.println("pages = " + pages.getContent());
  }

}
