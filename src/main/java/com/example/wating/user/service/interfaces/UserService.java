package com.example.wating.user.service.interfaces;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.user.dto.UserLoginRequestDto;
import com.example.wating.user.dto.UserRequestDto;
import com.example.wating.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface UserService {
  StatusResponseDto signUp(UserRequestDto userRequestDto); //회원가입

  StatusResponseDto login(HttpServletResponse response, UserLoginRequestDto request);

  User findUserByUserId(Long userId);
}
