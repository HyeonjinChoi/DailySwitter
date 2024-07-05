package com.sparta.dailyswitter.domain.comment.repository;

import static com.querydsl.jpa.JPAExpressions.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.dailyswitter.domain.comment.entity.Comment;
import com.sparta.dailyswitter.domain.comment.entity.QComment;
import com.sparta.dailyswitter.domain.like.commentlike.entity.QCommentLike;
import com.sparta.dailyswitter.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	QComment comment = QComment.comment;
	QCommentLike commentLike = QCommentLike.commentLike;

	@Override
	public Page<Comment> findLikedCommentsByUser(User user, Pageable pageable) {
		List<Comment> comments = queryFactory.selectFrom(comment)
			.where(comment.id.in(
				select(commentLike.id.comment.id)
					.from(commentLike)
					.where(commentLike.id.user.eq(user))
			))
			.orderBy(comment.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = queryFactory.selectFrom(comment)
			.where(comment.id.in(
				select(commentLike.id.comment.id)
					.from(commentLike)
					.where(commentLike.id.user.eq(user))
			))
			.fetchCount();

		return new PageImpl<>(comments, pageable, total);
	}
}
