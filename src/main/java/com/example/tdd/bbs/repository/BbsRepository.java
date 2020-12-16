package com.example.tdd.bbs.repository;

import com.example.tdd.bbs.model.BbsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbsRepository extends JpaRepository<BbsEntity, Long> {

}
