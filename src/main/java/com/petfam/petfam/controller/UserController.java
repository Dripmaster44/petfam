package com.petfam.petfam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petfam.petfam.dto.AdminSignupRequestDto;
import com.petfam.petfam.dto.SigninRequestDto;
import com.petfam.petfam.dto.UserSignupRequestDto;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserServiceImpl userService;

	@PostMapping("/signup")
	public ResponseEntity<String> userSignup(@RequestBody UserSignupRequestDto requestDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.userSignup(requestDto));
	}

	@PostMapping("/admin/signup")
	public ResponseEntity<String> adminSignup(@RequestBody AdminSignupRequestDto requestDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.adminSignup(requestDto));
	}

	@PostMapping("/signin")
	public ResponseEntity<String> signin(@RequestBody SigninRequestDto signinRequestDto, HttpServletResponse response){
		return ResponseEntity.status(HttpStatus.OK).body(userService.signin(signinRequestDto,response));
	}

	@PostMapping("/signout")
	public ResponseEntity<String> signout(@RequestBody HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(userService.signout(request));
	}

	@PatchMapping("/profiles")
	public ResponseEntity<String> updateProfile(@RequestBody ProfileUpdateDto profileUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(profileUpdateDto,user));
	}

	@GetMapping("/profiles/{userId}")
	public ResponseEntity<ProfileResponseDto> getProfile(@RequestBody ProfileResponseDto profileResponseDto, @AuthenticationPrincipal
		UserDetailsImpl userDetails){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getProfile());
	}

	@GetMapping("/profiles/{userId}")
	public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long userID){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getProfile(userID));
	}

	@PostMapping("/refresh")
	public ResponseEntity<String> refresh(@RequestBody HttpServletRequest request, HttpServletResponse response){
		return ResponseEntity.status(HttpStatus.OK).body(userService.refresh(request,response));
	}

}
