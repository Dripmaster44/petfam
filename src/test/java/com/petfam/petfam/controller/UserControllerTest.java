//package com.petfam.petfam.controller;
//
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.google.gson.Gson;
//import com.petfam.petfam.dto.user.UserSignupRequestDto;
//import com.petfam.petfam.service.user.UserServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ExtendWith(MockitoExtension.class)
//class UserControllerTest {
//
//  private MockMvc mockMvc;
//
//
//  @Mock
//  private UserServiceImpl userService;
//
//  @InjectMocks
//  private UserController userController;
//
//
//  @BeforeEach
//  public void init() {
//    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//  }
//  @Test
//  @DisplayName("회원가입")
//  void userSignup() throws Exception {
//    //giver
//    UserSignupRequestDto requestDto = new UserSignupRequestDto("user","123","kap");
//    when(userService.userSignup(requestDto)).thenReturn("success");
//
//    //when
//    ResultActions resultActions = mockMvc.perform(
//        MockMvcRequestBuilders.post("/users/signup")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(new Gson().toJson(requestDto)));
//
//    //then
//    resultActions.andExpect(status().isOk())
//        .andExpect(content().string("success"));
//    verify(userService).userSignup(requestDto);
//  }
//
//  @Test
//  void adminSignup() {
//  }
//
//  @Test
//  void signin() {
//  }
//
//  @Test
//  void adminSignin() {
//  }
//
//  @Test
//  void signout() {
//  }
//
//  @Test
//  void updateProfile() {
//  }
//
//  @Test
//  void getProfile() {
//  }
//
//  @Test
//  void testGetProfile() {
//  }
//
//  @Test
//  void refresh() {
//  }
//}