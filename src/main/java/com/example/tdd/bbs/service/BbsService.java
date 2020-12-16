package com.example.tdd.bbs.service;

import com.example.tdd.bbs.model.BbsDto;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.controller.BbsController.BbsListParam;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface BbsService {

  Page<BbsEntity> list(final BbsListParam bbsListParam);

  Optional<BbsEntity> detail(final long id);

  BbsEntity add(final BbsDto bbsDto);

  void edit(final BbsDto bbsDto);

  void delete(final long id);
}
