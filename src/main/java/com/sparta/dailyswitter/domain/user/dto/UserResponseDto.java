package com.sparta.dailyswitter.domain.user.dto;

import com.sparta.dailyswitter.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class UserResponseDto {
	private String userId;
	private String name;
	private String email;
	private String intro;

	public UserResponseDto(User user) {
		this.userId = user.getUserId();
		this.name = user.getUsername();
		this.email = user.getEmail();
		this.intro = user.getIntro();
	}
}
