package com.petfam.petfam.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import com.petfam.petfam.dto.user.UserResponseDto;
import com.petfam.petfam.service.user.AdminServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminServiceImpl adminService;

	//페이징처리
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsers());
	}
}