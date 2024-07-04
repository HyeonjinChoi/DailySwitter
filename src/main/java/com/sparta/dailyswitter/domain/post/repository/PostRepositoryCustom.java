package com.sparta.dailyswitter.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sparta.dailyswitter.domain.post.entity.Post;
import com.sparta.dailyswitter.domain.user.entity.User;

public interface PostRepositoryCustom {
	Page<Post> findLikedPostsByUser(User user, Pageable pageable);
}
