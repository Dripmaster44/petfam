package com.petfam.petfam.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfam.petfam.dto.user.AdminSigninRequestDto;
import com.petfam.petfam.dto.user.AdminSignupRequestDto;
import com.petfam.petfam.dto.user.SigninRequestDto;
import com.petfam.petfam.dto.user.UserSignupRequestDto;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.entity.enums.UserRoleEnum;
import com.petfam.petfam.security.UserDetailsImpl;
import com.petfam.petfam.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  private MockMvc mockMvc;
  @Mock
  private UserServiceImpl userService;

  @Mock
  private UserDetailsImpl userDetails;

  @InjectMocks
  private UserController userController;


  @BeforeEach
  public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }
  @Test
  @DisplayName("유저회원가입")
  void userSignup() throws Exception {
    // giver
    UserSignupRequestDto requestDto = UserSignupRequestDto.builder()
        .username("user")
        .password("123")
        .nickname("kap")
        .build();
    String requestBody = new ObjectMapper().writeValueAsString(requestDto);
    when(userService.userSignup(any(UserSignupRequestDto.class))).thenReturn("success");

    // when
    MvcResult result = mockMvc.perform(post("/users/signup")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(status().isOk())
        .andReturn();

    // then
    assertEquals(result.getResponse().getContentAsString(),"success");
  }



  @Test
  @DisplayName("관리자회원가입")
  void adminSignup() throws Exception {
    //giver
    AdminSignupRequestDto requestDto = AdminSignupRequestDto.builder()
        .username("admin")
        .password("123")
        .nickname("admin")
        .adminKey("AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC")
        .build();
    String requestBody = new ObjectMapper().writeValueAsString(requestDto);
    when(userService.adminSignup(any(AdminSignupRequestDto.class))).thenReturn("success");
    //when
    MvcResult result =mockMvc.perform(post("/users/admin/signup")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(requestBody))
        .andExpect(status().isOk())
        .andReturn();
    //then
    assertEquals(result.getResponse().getContentAsString(),"success");
  }

  @Test
  @DisplayName("유저로그인")
  void signin() throws Exception{
    //giver
    SigninRequestDto requestDto = SigninRequestDto.builder()
        .username("user")
        .password("123")
        .build();
    String requestBody = new ObjectMapper().writeValueAsString(requestDto);
    when(userService.signin(any(SigninRequestDto.class),any(HttpServletResponse.class))).thenReturn("success");
    //when
    MvcResult result = mockMvc.perform(post("/users/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andReturn();
    //then
    assertEquals(result.getResponse().getContentAsString(),"success");
  }

  @Test
  @DisplayName("관리자로그인")
  void adminSignin() throws Exception{
    //giver
    AdminSigninRequestDto requestDto = AdminSigninRequestDto.builder()
        .username("admin")
        .password("123")
        .adminKey("AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC")
        .build();
    String requestBody = new ObjectMapper().writeValueAsString(requestDto);
    when(userService.adminSignin(any(AdminSigninRequestDto.class),any(HttpServletResponse.class))).thenReturn("success");
    //when
    MvcResult result = mockMvc.perform(post("/users/admin/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andReturn();
    //then
    assertEquals(result.getResponse().getContentAsString(),"success");
  }

  @Test
  @DisplayName("로그아웃")
  void signout() throws Exception {
    // given
    User user = new User("user", "password",
        "kap", "image",
        UserRoleEnum.USER);
    UserDetailsImpl userDetails = new UserDetailsImpl(user,"user");
    when(userService.signout(any(HttpServletRequest.class), any(String.class))).thenReturn("success");

    // when
    MvcResult result = mockMvc.perform(post("/users/signout")
            .with(csrf())
            .with(user(userDetails)))
        .andExpect(status().isOk())
        .andReturn();

    // then
    assertEquals(result.getResponse().getContentAsString(),"success");
  }



  @Test
  @DisplayName("프로필업데이트")
  void updateProfile() {
  }

  @Test
  @DisplayName("프로필 가져오기")
  void getProfile() {
  }


  @Test
  void refresh() {
  }
}