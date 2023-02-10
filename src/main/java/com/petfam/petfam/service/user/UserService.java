package com.petfam.petfam.service.user;


import com.petfam.petfam.dto.user.AdminSignupRequestDto;
import com.petfam.petfam.dto.user.ProfileResponseDto;
import com.petfam.petfam.dto.user.ProfileUpdateDto;
import com.petfam.petfam.dto.user.SigninRequestDto;
import com.petfam.petfam.dto.user.UserSignupRequestDto;
import com.petfam.petfam.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
  public String userSignup(UserSignupRequestDto usersignupRequestDto);
  public String adminSignup(AdminSignupRequestDto adminsignupRequestDto);
  public String signin(SigninRequestDto signinRequestDto, HttpServletResponse response);
  public String signout(HttpServletRequest request,String username);
  public String updateProfile(ProfileUpdateDto profileUpdateDto, User user);
  public ProfileResponseDto getProfile(Long userId);
  public String refresh(HttpServletRequest request,HttpServletResponse response);
}
