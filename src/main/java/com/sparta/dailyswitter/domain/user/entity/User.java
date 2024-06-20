package com.sparta.dailyswitter.domain.user.entity;

import com.sparta.dailyswitter.common.util.Timestamped;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "user_id")
	private String userId;

	@NotBlank
	@Column(name = "user_name")
	private String username;

	@NotBlank
	@Column(name = "password")
	private String password;

	@NotBlank
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "intro")
	private String intro;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private UserRoleEnum role;

	@Column(name = "refresh_token")
	private String refreshToken;

	public boolean isExist() {
		return this.role == UserRoleEnum.USER;
	}

	public void updateRefresh(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}