package com.petfam.petfam.service.user;

import com.petfam.petfam.dto.user.UserResponseDto;
import java.util.List;

public interface AdminService {

  public List<UserResponseDto> getUsers();
}
