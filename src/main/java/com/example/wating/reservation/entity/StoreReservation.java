package com.example.wating.reservation.entity;

import com.example.wating.store.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Entity
@Document(collection = "mongoTest")
public class StoreReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Short years;

  @Column
  private Byte months;

  @Column
  private String storeReservationInfos;

  @Builder
  public StoreReservation(Short years, Byte months, Store store,String storeReservationInfos) {
    this.years = years;
    this.months = months;
    this.store = store;
    this.storeReservationInfos = storeReservationInfos;
  }

  //메서드
  public void update(String storeReservationInfos){
    this.storeReservationInfos = storeReservationInfos;
  }

  //연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;

}
