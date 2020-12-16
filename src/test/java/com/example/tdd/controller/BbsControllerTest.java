package com.example.tdd.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tdd.bbs.model.BbsDto;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.service.BbsService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BbsControllerTest {

  @Autowired
  MockMvc mockMvc;

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
  public void list() throws Exception {
    mockMvc
        .perform(
            get("/bbs")
                .param("page", "1")
                .param("size", "10")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", Matchers.hasSize(10)))
        .andDo(print());
  }

  @Test
  public void detail() throws Exception {
    mockMvc
        .perform(
            get("/bbs/{id}", 1)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(1)))
        .andDo(print());
  }
}