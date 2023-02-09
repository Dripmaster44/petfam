package com.petfam.petfam.service.user;

import com.petfam.petfam.dto.AdminSignupRequestDto;
import com.petfam.petfam.dto.ProfileResponseDto;
import com.petfam.petfam.dto.ProfileUpdateDto;
import com.petfam.petfam.dto.SigninRequestDto;
import com.petfam.petfam.dto.UserSignupRequestDto;
import com.petfam.petfam.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
  public String usersignup(UserSignupRequestDto usersignupRequestDto);
  public String adminsignup(AdminSignupRequestDto adminsignupRequestDto);
  public String signin(SigninRequestDto signinRequestDto, HttpServletResponse response);
  public String signout(HttpServletRequest request);
  public String updateProfile(ProfileUpdateDto profileUpdateDto, User user);
  public ProfileResponseDto getProfile(Long userId);
  public String refresh(HttpServletRequest request,HttpServletResponse response);
}
