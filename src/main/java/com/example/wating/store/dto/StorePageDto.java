package com.example.wating.store.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
public class StorePageDto {
  int page;

  int size;

  public Pageable toPageable() {
      return PageRequest.of(page, size,
          Sort.by("createdAt").descending());
  }
}
