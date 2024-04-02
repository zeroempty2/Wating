package com.example.wating.store.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class StoreResponseDto {
  private Long id;

  private String storeName; //가맹점이름

  private Float starRate; //별점
}
