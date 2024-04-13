package com.example.wating.store.entity;

import com.example.wating.user.entity.User;
import com.example.wating.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  private String storeLocation; //가맹점위치정보

  @Column
  private Float starRate; //별점

  @Column
  private String timeDetail; // 변수명 변경 예정, 운영시간

  @Column
  private String storePhoneNumber;  //가게전화번호

  @Column
  private String aboutStore;  //가게소개

  @Column
  private String storeNotification; //가맹점공지

  @Column
  private String reservationTypeFlag; //예약 지정일자 오픈 여부(Y,N)

  @Column
  private String regularHoliday; //정기 휴무일

  @Column
  private String storeHomepage; //홈페이지 주소

  @Builder
  public Store(String storeName,String aboutStore,User user) {
    this.storeName = storeName;
    this.aboutStore = aboutStore;
    this.user = user;
  }

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;

}
