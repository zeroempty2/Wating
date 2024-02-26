package com.example.wating.reservation.entity;

import com.example.wating.store.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "mongoTest")
public class StoreReservation {
  @Column
  private Short years;

  @Column
  private Byte months;

  @Column
  private HashSet<StoreReservationInfo> infos;

  @Builder
  public StoreReservation(Short years, Byte months, Store store, HashSet<StoreReservationInfo> infos) {
    this.years = years;
    this.months = months;
    this.store = store;
    this.infos = infos;
  }

  //메서드
  public void update(HashSet<StoreReservationInfo> infos){
    this.infos = infos;
  }

  //연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;

}
