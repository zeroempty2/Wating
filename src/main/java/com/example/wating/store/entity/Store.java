package com.example.wating.store.entity;

import com.example.wating.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Store extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store", nullable = false)
  private Long id;

  @Column
  private String storeName; //가맹점이름

  @Column
  private Float starRate; //별점

  @Column
  private String aboutStore;  //가게소개

  @Builder
  public Store(Long id, String storeName, Float starRate, String aboutStore) {
    this.id = id;
    this.storeName = storeName;
    this.starRate = starRate;
    this.aboutStore = aboutStore;
  }
}
