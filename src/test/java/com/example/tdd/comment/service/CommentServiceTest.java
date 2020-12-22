package com.example.tdd.comment.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.example.tdd.comment.model.CommentEntity;
import com.example.tdd.comment.repository.CommentRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

  @Mock
  CommentEntity commentEntity;

  @Mock
  CommentRepository commentRepository;

  @InjectMocks
  CommentService commentService = new CommentService() {
    @Override
    public List<CommentEntity> list() {
      return commentRepository.findAll();
    }
  };

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void list() {

    //given
    final List<CommentEntity> list = IntStream.rangeClosed(1, 10)
        .mapToObj(i -> {
              when(commentEntity.getId()).thenReturn((long) i);

              //then
              assertEquals(i, commentEntity.getId());
              return commentEntity;
            }
        )
        .peek(commentDto -> System.out.println("commentDto.getId() = " + commentDto.getId()))
        .collect(Collectors.toList());

    when(commentRepository.findAll()).thenReturn(list);

    //then
    assertArrayEquals(list.stream().map(CommentEntity::getId).toArray(Long[]::new),
        commentService.list().stream().map(CommentEntity::getId).toArray(Long[]::new));
  }
}