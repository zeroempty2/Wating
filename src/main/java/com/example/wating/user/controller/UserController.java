package com.example.wating.user.controller;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.user.dto.UserLoginRequestDto;
import com.example.wating.user.dto.UserRequestDto;
import com.example.wating.user.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/signUp")
  public void signUp(@RequestBody @Valid UserRequestDto userRequestDto) {
    userService.signUp(userRequestDto);
  }

  @PostMapping("/login")
  public ResponseEntity<StatusResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto,
      HttpServletResponse response) {
    StatusResponseDto statusResponseDto = userService.login(response, loginRequestDto);
    return ResponseEntity.ok().body(statusResponseDto);
  }
}
