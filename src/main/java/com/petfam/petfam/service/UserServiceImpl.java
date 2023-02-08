package com.petfam.petfam.service;


import com.petfam.petfam.dto.AdminSignupRequestDto;
import com.petfam.petfam.dto.SigninRequestDto;
import com.petfam.petfam.dto.UserSignupRequestDto;
import com.petfam.petfam.entity.User;
import com.petfam.petfam.entity.enums.UserRoleEnum;
import com.petfam.petfam.jwt.JwtUtil;
import com.petfam.petfam.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

  @Override
  public String usersignup(UserSignupRequestDto usersignupRequestDto) {

    String password = passwordEncoder.encode(usersignupRequestDto.getPassword());

    _ck_username(usersignupRequestDto.getUsername());
    _ck_nickname(usersignupRequestDto.getNickname());

    User user = new User(usersignupRequestDto.getUsername(),password,usersignupRequestDto.getNickname(),"image",
        UserRoleEnum.USER);

    userRepository.save(user);

    return "회원가입완료";
  }

  @Override
  public String adminsignup(AdminSignupRequestDto adminsignupRequestDto) {
    String password = passwordEncoder.encode(adminsignupRequestDto.getPassword());

    _ck_username(adminsignupRequestDto.getUsername());
    _ck_nickname(adminsignupRequestDto.getNickname());

    if(adminsignupRequestDto.getAdminKey().equals(ADMIN_TOKEN)) {
      User admin = new User(adminsignupRequestDto.getUsername(),password,adminsignupRequestDto.getNickname(),"image",
          UserRoleEnum.ADMIN);

      userRepository.save(admin);

      return "관리자 회원가입완료";
    } else {return "관리자 암호가 일치하지 않습니다.";}
  }

  @Override
  public String signin(SigninRequestDto signinRequestDto, HttpServletResponse response) {


    User user = userRepository.findByUsername(signinRequestDto.getUsername()).orElseThrow(
        () -> new IllegalArgumentException("아이디와 비밀번호를 확인해주세요")
    );
    if(!passwordEncoder.matches(signinRequestDto.getPassword(),user.getPassword())) {
      throw new IllegalArgumentException("아이디와 비밀번호를 확인해주세요");
    }
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(),user.getUserRole()));
    response.addHeader(JwtUtil.REFRESH_AUTHORIZATION_HEADER,jwtUtil.refreshToken(user.getUsername(),user.getUserRole()));
    return "로그인완료";
  }

  @Override
  public String signout(HttpServletRequest request) {
    return null;
  } //추후 구현

  @Override
  public String refresh(HttpServletRequest request, HttpServletResponse response) {
    String accessToken = jwtUtil.resolveToken(request);   //엑세스토큰
    String refreshToken = jwtUtil.resolveRefreshToken(request); //리프레시토큰

    if(!jwtUtil.validateToken(refreshToken)) {return "다시 로그인 해주세요.";}

    Claims accessInfo = jwtUtil.getUserInfoFromToken(accessToken);
    Claims refreshInfo = jwtUtil.getUserInfoFromToken(refreshToken);

    if(accessInfo.equals(refreshInfo)) {
        User user = _findUser(accessInfo.getSubject());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(),user.getUserRole()));
        return "로그인이 연장되었습니다.";
    } else {return "다시 로그인 해주세요.";}
  }

  private void _ck_username(String username) {
    if(userRepository.findByUsername(username).isPresent()) {
      throw new IllegalArgumentException("이미 존재하는 유저입니다.");
    }
  }
  private void _ck_nickname(String nickname) {
    if(userRepository.findByUsername(nickname).isPresent()) {
      throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
    }
  }
  private User _findUser(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException("유저 정보가 존재하지 않습니다.")
    );
    return user;
  }
}
