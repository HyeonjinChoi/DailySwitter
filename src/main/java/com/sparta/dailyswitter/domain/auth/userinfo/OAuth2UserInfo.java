package com.sparta.dailyswitter.domain.auth.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {
	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public abstract String getProviderId();

	public abstract String getName();

	public abstract String getEmail();
}
