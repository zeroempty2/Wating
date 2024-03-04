package com.example.wating.comment.dto;

public record CommentRequestDto(String commentContents,boolean isChild,Long parentId) {

}
