package com.petfam.petfam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petfam.petfam.dto.user.AdminSigninRequestDto;
import com.petfam.petfam.dto.user.AdminSignupRequestDto;
import com.petfam.petfam.dto.user.ProfileResponseDto;
import com.petfam.petfam.dto.user.ProfileUpdateDto;
import com.petfam.petfam.dto.user.SigninRequestDto;
import com.petfam.petfam.dto.user.UserNicknameDto;
import com.petfam.petfam.dto.user.UserSignupRequestDto;
import com.petfam.petfam.dto.user.UserUsernameDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.user.KakaoService;
import com.petfam.petfam.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserServiceImpl userService;
  private final KakaoService kakaoService;


  @PostMapping("/signup")
  public String userSignup(@RequestBody UserSignupRequestDto requestDto) {
    userService.userSignup(requestDto);
    return "success";
  }


  @PostMapping("/admin/signup")
  public String adminSignup(@RequestBody AdminSignupRequestDto requestDto) {
    userService.adminSignup(requestDto);
    return "success";
  }

  @PostMapping("/signin")
  public String signin(@RequestBody SigninRequestDto signinRequestDto,
      HttpServletResponse response) {
     userService.signin(signinRequestDto, response);
     return "success";
  }

  @PostMapping("/admin/signin")
  public String adminSignin(@RequestBody AdminSigninRequestDto requestDto,
      HttpServletResponse response) {
      userService.AdminSignin(requestDto, response);
      return "success";
  }

	@PostMapping("/signout")
	public ResponseEntity<String> signout(HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails){
		return ResponseEntity.status(HttpStatus.OK).body(userService.signout(request,userDetails.getUsername()));
	}


  @PatchMapping("/profiles")
  public ResponseEntity<String> updateProfile(@RequestBody ProfileUpdateDto profileUpdateDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.updateProfile(profileUpdateDto, userDetails.getUser()));
  }

  @GetMapping("/profiles")
  public ResponseEntity<ProfileResponseDto> getProfile(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.getProfile(userDetails.getUser().getId()));
  }


  @GetMapping("/profiles/{userId}")
  public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getProfile(userId));
  }


  @PostMapping("/refresh")
  public ResponseEntity<String> refresh(HttpServletRequest request, HttpServletResponse response) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.refresh(request, response));
  }

  @PostMapping("/id")
  public ResponseEntity<String> ck_id(@RequestBody UserUsernameDto userUsernameDto) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.ck_username(userUsernameDto));
  }

  @PostMapping("/nickname")
  public ResponseEntity<String> ck_nickname(@RequestBody UserNicknameDto userNicknameDto) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.ck_nickname(userNicknameDto));
  }

  @GetMapping("/kakao/callback")
  public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
    // code: 카카오 서버로부터 받은 인가 코드
    kakaoService.kakaoLogin(code, response);

    return "redirect:http://http://127.0.0.1:5501/index.html";
  }
}