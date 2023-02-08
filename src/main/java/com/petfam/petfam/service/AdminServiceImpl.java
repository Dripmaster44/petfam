package com.petfam.petfam.service;


import com.petfam.petfam.dto.UserResponseDto;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

  private final UserRepository userRepository;
  @Override
  public List<UserResponseDto> getUsers() {

    List<User> users = userRepository.findAll();
    List<UserResponseDto> userResponseDtos = new ArrayList<>();

    for(User user : users) {
      userResponseDtos.add(new UserResponseDto(user));
    }
    return userResponseDtos;
  }

}
