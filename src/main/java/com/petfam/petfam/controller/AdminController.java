package com.petfam.petfam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petfam.petfam.dto.UserResponseDto;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.AdminServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminServiceImpl adminService;


	@Secured("ROLE_ADMIN")
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getUsers(@RequestBody UserResponseDto userResponseDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsers());
	}
}
