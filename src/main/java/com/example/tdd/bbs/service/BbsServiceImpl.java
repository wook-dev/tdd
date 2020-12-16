package com.example.tdd.bbs.service;

import com.example.tdd.bbs.model.BbsConverter;
import com.example.tdd.bbs.model.BbsDto;
import com.example.tdd.bbs.model.BbsEntity;
import com.example.tdd.bbs.repository.BbsRepository;
import com.example.tdd.controller.BbsController.BbsListParam;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BbsServiceImpl implements BbsService {

  private final BbsRepository bbsRepository;

  @Override
  public Page<BbsEntity> list(final BbsListParam bbsListParam) {
    return bbsRepository
        .findAll(new PageRequest(bbsListParam.getPage() - 1, bbsListParam.getSize()));
  }

  @Override
  public Optional<BbsEntity> detail(final long id) {
    return Optional.empty();
  }

  @Override
  public BbsEntity add(final BbsDto bbsDto) {
    return bbsRepository.save(BbsConverter.convertToAdd(bbsDto));
  }

  @Override
  public void edit(final BbsDto bbsDto) {

  }

  @Override
  public void delete(final long id) {

  }
}
