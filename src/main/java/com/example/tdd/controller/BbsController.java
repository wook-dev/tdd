package com.example.tdd.controller;

import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.service.BbsService;
import com.example.tdd.controller.param.BbsListParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsController {

  private final BbsService bbsService;

  @GetMapping("")
  public ResponseEntity<Page<BbsEntity>> list(@ModelAttribute final BbsListParam bbsListParam) {
    return ResponseEntity.ok(bbsService.list(bbsListParam));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BbsEntity> detail(@PathVariable final long id) {
    return ResponseEntity
        .ok(bbsService.detail(id).orElseThrow(() -> new RuntimeException("해당 게시물이 없습니다.")));
  }
}
