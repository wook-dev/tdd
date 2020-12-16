package com.example.tdd.controller;

import com.example.tdd.bbs.model.BbsConverter;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.service.BbsService;
import com.example.tdd.controller.param.BbsAddParam;
import com.example.tdd.controller.param.BbsEditParam;
import com.example.tdd.controller.param.BbsListParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping("")
  public ResponseEntity<BbsEntity> add(@RequestBody final BbsAddParam bbsAddParam) {
    return ResponseEntity.ok(bbsService.add(BbsConverter.convertToBbsDtoForAdd(bbsAddParam)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Boolean> edit(@PathVariable final long id,
      @RequestBody final BbsEditParam bbsEditParam) {
    bbsService.edit(BbsConverter.convertToBbsDtoForEdit(id, bbsEditParam));
    return ResponseEntity.ok(true);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable final long id) {
    bbsService.delete(id);
    return ResponseEntity.ok(true);
  }
}
