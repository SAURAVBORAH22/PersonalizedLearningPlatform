package com.example.PersonalizedLearningPlatform.user.service.impl;

import com.example.PersonalizedLearningPlatform.user.entity.User;
import com.example.PersonalizedLearningPlatform.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), new ArrayList<>());
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}