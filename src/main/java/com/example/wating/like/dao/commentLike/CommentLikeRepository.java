package com.example.wating.like.dao.commentLike;

import com.example.wating.comment.dao.CommentRepositoryQuery;
import com.example.wating.comment.entity.Comment;
import com.example.wating.like.entity.commentLike.CommentLike;
import com.example.wating.like.entity.commentLike.CommentLikeId;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CommentLikeRepository extends Repository<CommentLike, CommentLikeId>,CommentLikeRepositoryQuery {
  void save(CommentLike commentLike);

  void deleteByCommentLikeId(CommentLikeId commentLikeId);

}
