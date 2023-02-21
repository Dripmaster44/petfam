package com.petfam.petfam.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import com.petfam.petfam.dto.user.UserResponseDto;
import com.petfam.petfam.service.user.AdminServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

	private final AdminServiceImpl adminService;

	//페이징처리
	@GetMapping("/users")
	public ResponseEntity<Page<UserResponseDto>> getUsers(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsers(pageable));
	}
}