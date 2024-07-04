package com.sparta.dailyswitter.domain.post.repository;

import static com.querydsl.jpa.JPAExpressions.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.dailyswitter.domain.like.postlike.entity.QPostLike;
import com.sparta.dailyswitter.domain.post.entity.Post;
import com.sparta.dailyswitter.domain.post.entity.QPost;
import com.sparta.dailyswitter.domain.user.entity.QUser;
import com.sparta.dailyswitter.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	QPost post = QPost.post;
	QPostLike postLike = QPostLike.postLike;

	@Override
	public Page<Post> findLikedPostsByUser(User user, Pageable pageable) {
		List<Post> posts = queryFactory.selectFrom(post)
			.where(post.id.in(
				select(postLike.id.post.id)
					.from(postLike)
					.where(postLike.id.user.eq(user))
			))
			.orderBy(post.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = queryFactory.selectFrom(post)
			.where(post.id.in(
				select(postLike.id.post.id)
					.from(postLike)
					.where(postLike.id.user.eq(user))
			))
			.fetchCount();

		return new PageImpl<>(posts, pageable, total);
	}
}
