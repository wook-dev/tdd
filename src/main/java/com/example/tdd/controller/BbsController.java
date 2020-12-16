package com.example.tdd.controller;

import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.service.BbsService;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsController {

  private final BbsService bbsService;

  @GetMapping("")
  public ResponseEntity<Page<BbsEntity>> list(@ModelAttribute final BbsListParam bbsListParam) {
    System.out.println("bbsListParam = " + bbsListParam);
    return ResponseEntity.ok(bbsService.list(bbsListParam));
  }

  @ToString
  @NoArgsConstructor
  public static class BbsListParam {

    @Builder
    public BbsListParam(final int page, final int size) {
      this.page = page;
      this.size = size;
    }

    private int page;
    private int size;

    public int getPage() {
      return Math.max(page, 1);
    }

    public int getSize() {
      return Math.max(size, 10);
    }
  }
}
