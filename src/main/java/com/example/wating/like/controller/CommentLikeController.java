package com.example.wating.like.controller;

import com.example.wating.common.dto.StatusResponseDto;
import com.example.wating.like.service.interfaces.CommentLikeService;
import com.example.wating.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommentLikeController.COMMENT_LIKE_URI_API)
public class CommentLikeController {
  public static final String COMMENT_LIKE_URI_API = "/comments/likes";
  private final CommentLikeService commentLikeService;
  @PostMapping("/{commentId}")
  public ResponseEntity<StatusResponseDto> requestCommentLike(@PathVariable Long commentId,@AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = commentLikeService.requestCommentLike(userDetails.getUserId(),commentId);
    return ResponseEntity.ok().body(statusResponseDto);
  }
}

