package com.example.wating.like.dao.commentLike;

import com.example.wating.comment.dto.CommentResponseDto;
import com.example.wating.like.entity.commentLike.CommentLikeId;
import java.util.List;
import java.util.Optional;

public interface CommentLikeRepositoryQuery {
  boolean existByCommentLikeId(CommentLikeId commentLikeId);
}
