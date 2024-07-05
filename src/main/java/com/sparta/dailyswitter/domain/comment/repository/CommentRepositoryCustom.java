package com.sparta.dailyswitter.domain.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sparta.dailyswitter.domain.comment.entity.Comment;
import com.sparta.dailyswitter.domain.user.entity.User;

public interface CommentRepositoryCustom {
	Page<Comment> findLikedCommentsByUser(User user, Pageable pageable);
}
