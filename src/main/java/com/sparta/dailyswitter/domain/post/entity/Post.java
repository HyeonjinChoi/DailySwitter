package com.sparta.dailyswitter.domain.post.entity;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.sparta.dailyswitter.common.util.Timestamped;
import com.sparta.dailyswitter.domain.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "title")
	private String title;

	@Column(nullable = false, name = "contents")
	private String contents;

	@Column(name = "is_pinned")
	private boolean isPinned = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


	@Builder
	public Post(String title, String contents, User user) {
		this.title = title;
		this.contents = contents;
		this.user = user;
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public void togglePin() {
		this.isPinned = !this.isPinned;
	}
}
