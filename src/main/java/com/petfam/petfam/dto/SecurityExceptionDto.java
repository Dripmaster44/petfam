package com.petfam.petfam.dto;

public class SecurityExceptionDto {
  private String msg;
  private int statusCode;
  public SecurityExceptionDto(int statusCode, String msg) {
    this.msg = msg;
    this.statusCode = statusCode;
  }
}
