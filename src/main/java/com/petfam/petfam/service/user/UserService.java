package com.petfam.petfam.service.user;

import com.petfam.petfam.dto.user.AdminSignupRequestDto;
import com.petfam.petfam.dto.user.SigninRequestDto;
import com.petfam.petfam.dto.user.UserSignupRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

  public String usersignup(UserSignupRequestDto usersignupRequestDto);

  public String adminsignup(AdminSignupRequestDto adminsignupRequestDto);

  public String signin(SigninRequestDto signinRequestDto, HttpServletResponse response);

  public String signout(HttpServletRequest request);

  public String refresh(HttpServletRequest request, HttpServletResponse response);
}
