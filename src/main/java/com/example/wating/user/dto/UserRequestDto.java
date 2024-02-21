package com.example.wating.user.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(String username,String password,String nickName, @NotEmpty String phoneNumber, String aboutMe, String profileUrl) {

}

