package com.example.wating.user.dto;

import com.example.wating.util.customAnnotations.validateNickname.ValidateNickname;
import com.example.wating.util.customAnnotations.validatePassword.ValidatePassword;
import com.example.wating.util.customAnnotations.validateUsername.ValidateUsername;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(@ValidateUsername String username, @ValidatePassword String password, @ValidateNickname String nickName, @NotEmpty String phoneNumber, String aboutMe, String profileUrl) {

}

