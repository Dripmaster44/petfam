package com.petfam.petfam.service;

import com.petfam.petfam.dto.UserResponseDto;
import java.util.List;

public interface AdminService {
  public List<UserResponseDto> getUsers();
}
