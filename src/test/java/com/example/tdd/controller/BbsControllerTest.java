package com.example.tdd.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tdd.bbs.model.BbsDto;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.service.BbsService;
import com.example.tdd.controller.param.BbsAddParam;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
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
    //given
    final int page = 1;
    final int size = 10;

    mockMvc
        //when
        .perform(
            get("/bbs")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
        )
        //then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", Matchers.hasSize(10)))
        .andDo(print());
  }

  @Test
  public void detail() throws Exception {
    //given
    final int id = 1;

    mockMvc
        //when
        .perform(
            get("/bbs/{id}", id)
        )
        //then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(id)))
        .andDo(print());
  }

  @Test
  public void add() throws Exception {
    //given
    final String writer = "김태욱";
    final String title = "게시판 제목";
    final String content = "게시판 내용";
    final String param = new ObjectMapper()
        .valueToTree(
            BbsAddParam
                .builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build()
        )
        .toString();

    mockMvc
        //when
        .perform(
            post("/bbs")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(param)
        )
        //then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(21)))
        .andExpect(jsonPath("$.writer", Matchers.is(writer)))
        .andExpect(jsonPath("$.title", Matchers.is(title)))
        .andExpect(jsonPath("$.content", Matchers.is(content)))
        .andDo(print());
  }
}