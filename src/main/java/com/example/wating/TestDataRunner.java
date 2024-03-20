package com.example.wating;

import com.example.wating.comment.dao.CommentRepository;
import com.example.wating.comment.entity.Comment;
import com.example.wating.review.dao.ReviewRepository;
import com.example.wating.review.entity.Review;
import com.example.wating.store.dao.StoreRepository;
import com.example.wating.store.entity.Store;
import com.example.wating.user.dao.UserRepository;
import com.example.wating.user.entity.User;
import com.example.wating.user.service.UserServiceImpl;
import com.example.wating.util.enums.UserRoleEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {

  private final UserRepository userRepository;
  private final UserServiceImpl userService;
  private final PasswordEncoder passwordEncoder;
  private final StoreRepository storeRepository;
  private final ReviewRepository reviewRepository;
  private final CommentRepository commentRepository;
  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    User user = User.builder()
        .role(UserRoleEnum.OWNER)
        .nickName("OWNER1")
        .username("OWNER1")
        .password(passwordEncoder.encode("Password!23"))
        .build();

    makeUsers();

    Store store = Store.builder().ownerId(user.getId()).storeName("Test").build();

    userRepository.saveAndFlush(user);
    storeRepository.saveAndFlush(store);

    User findUser = userRepository.findByUsername("CUSTOMER1").orElseThrow(() -> new IllegalArgumentException("유효하지 않은 정보입니다"));
    User findUser2 = userRepository.findByUsername("CUSTOMER2").orElseThrow(() -> new IllegalArgumentException("유효하지 않은 정보입니다"));
    Store findStore = storeRepository.findByOwnerId(store.getOwnerId()).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 정보입니다"));
    Review review = Review.builder()
        .userId(findUser.getId())
        .storeId(findStore.getId())
        .reviewContent("리뷰내용")
        .reviewTitle("리뷰제목")
        .build();

    Review review2 = Review.builder()
        .userId(findUser2.getId())
        .storeId(findStore.getId())
        .reviewContent("리뷰내용2")
        .reviewTitle("리뷰제목2")
        .build();

    reviewRepository.saveAndFlush(review);
    reviewRepository.saveAndFlush(review2);
  }

  public void makeUsers(){
    int count = 1;
    for(int i = 0; i < 50; i++){
      String name = "CUSTOMER" + count;
      User user = User.builder()
          .nickName(name)
          .username(name)
          .password(passwordEncoder.encode("Password!23"))
          .build();
          userRepository.saveAndFlush(user);
      count++;
    }
  }

  public void makeComments(Review review){
    for(int i = 0; i < 10; i++){
      Comment comment = Comment.builder()
          .commentContent("코멘트내용")
          .userId(5L)
          .review(review)
          .build();
      commentRepository.save(comment);
    }
  }

}
