package com.example.wating.store.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreDetailsResponseDto {
  private String storeName; //가맹점이름
  private Float starRate; //별점
  private String aboutStore;  //가게소개
}
