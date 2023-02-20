package com.petfam.petfam.service.user;

import com.petfam.petfam.dto.user.AdminSignupRequestDto;
import com.petfam.petfam.dto.user.ProfileResponseDto;
import com.petfam.petfam.dto.user.ProfileUpdateDto;
import com.petfam.petfam.dto.user.UserSignupRequestDto;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.entity.enums.UserRoleEnum;
import com.petfam.petfam.jwt.JwtUtil;
import com.petfam.petfam.repository.RefreshTokenRedisRepository;
import com.petfam.petfam.repository.SignoutAccessTokenRedisRepository;
import com.petfam.petfam.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private JwtUtil jwtUtil;
  @Mock
  private RefreshTokenRedisRepository refreshTokenRedisRepository;
  @Mock
  private SignoutAccessTokenRedisRepository signoutAccessTokenRedisRepository;


  @InjectMocks
  private UserServiceImpl userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("유저회원가입")
  void userSignup() {
    //giver
    UserSignupRequestDto requestDto = new UserSignupRequestDto("user","123","kap");
    //when
    String result = userService.userSignup(requestDto);
    //then
    assertEquals("회원가입완료",result);
  }

  @Test
  @DisplayName("관리자회원가입")
  void adminSignup() {
    //giver
    AdminSignupRequestDto requestDto = new AdminSignupRequestDto("admin","123","admin","AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC");
    String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    //when
    String result = userService.adminSignup(requestDto);
    //then
    assertEquals("관리자 회원가입완료",result);
  }

  @Test
  @DisplayName("유저로그인")
  void signin() {
    //giver

    //when

    //then
  }

  @Test
  @DisplayName("관리자로그인")
  void adminSignin() {
  }

  @Test
  @DisplayName("로그아웃")
  void signout() {
  }

  @Test
  @DisplayName("프로필업데이트")
  void updateProfile() {
    //giver
    ProfileUpdateDto request = new ProfileUpdateDto("kap11","안녕하세요","image11");
    User user = new User("user","123","kap","image", UserRoleEnum.USER);
    //when
    String result = userService.updateProfile(request,user);
    //then
    assertEquals("success",result);
  }

  @Test
  @DisplayName("프로필가져오기")
  void getProfile() {
    //giver
    Long userId = 1L;
    User user = new User("user","123","kap","image", UserRoleEnum.USER);
    user.setId(userId);
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    //when
    ProfileResponseDto result = userService.getProfile(user.getId());
    //then
    ProfileResponseDto responseDto = new ProfileResponseDto(user);
    assertEquals(responseDto.getNickname(),result.getNickname());
    assertEquals(responseDto.getImage(),result.getImage());
    assertEquals(responseDto.getIntroduction(),result.getIntroduction());
    assertEquals(responseDto.getRole(),result.getRole());
  }

  @Test
  @DisplayName("리프레시")
  void refresh() {
  }
}