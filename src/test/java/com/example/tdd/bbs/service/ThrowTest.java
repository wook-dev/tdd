package com.example.tdd.bbs.service;

import javax.validation.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ThrowTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void throwTest() {

    final boolean flag = true;
    if (flag == false) {
      System.out.println("ENTRY");
      thrown.expect(ValidationException.class);
      thrown.expectMessage("필수로 입력 해세요");
      throw new ValidationException("필수로 입력 해세요");
    }

    System.out.println("END");

  }
}
