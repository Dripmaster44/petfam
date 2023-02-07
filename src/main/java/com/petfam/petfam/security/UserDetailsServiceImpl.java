package com.petfam.petfam.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User client = clientRepository.findByUsername(username).orElseThrow(
        () -> new NullPointerException("고객이 존재하지 않습니다.")
    );
    return new UserDetailsImpl(client, client.getUsername());
  }
}