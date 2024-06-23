package com.example.PersonalizedLearningPlatform.common.controller;

import com.example.PersonalizedLearningPlatform.common.config.AuthenticationRequest;
import com.example.PersonalizedLearningPlatform.common.config.AuthenticationResponse;
import com.example.PersonalizedLearningPlatform.common.model.PlpServiceResponseObject;
import com.example.PersonalizedLearningPlatform.common.service.AuthService;
import com.example.PersonalizedLearningPlatform.common.util.JwtUtil;
import com.example.PersonalizedLearningPlatform.user.entity.User;
import com.example.PersonalizedLearningPlatform.user.model.UserResponseModel;
import com.example.PersonalizedLearningPlatform.user.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RequestMapping("/v1")
@RestController
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
              authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(401).body("Incorrect username or password");
    }
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @PostMapping("/register")
  public ResponseEntity<PlpServiceResponseObject<UserResponseModel>> registerUser(
      @RequestBody User user) {
    log.info("Registering user:{}", user);
    PlpServiceResponseObject<UserResponseModel> response = authService.registerUser(user);
    return new ResponseEntity<>(response, response.getStatusCode());
  }
}