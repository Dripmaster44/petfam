package com.petfam.petfam.service.user;


import com.petfam.petfam.dto.user.UserResponseDto;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService {

  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public List<UserResponseDto> getUsers() {

    List<User> users = userRepository.findAll();
    List<UserResponseDto> userResponseDtos = new ArrayList<>();

    for (User user : users) {
      userResponseDtos.add(new UserResponseDto(user));
    }
    return userResponseDtos;
  }

}
