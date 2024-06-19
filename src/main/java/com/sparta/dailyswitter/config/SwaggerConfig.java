package com.sparta.dailyswitter.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		// 액세스 토큰 보안 스키마 설정
		SecurityScheme accessTokenScheme = new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
			.in(SecurityScheme.In.HEADER)
			.name("Authorization");

		// 리프레시 토큰 보안 스키마 설정
		SecurityScheme refreshTokenScheme = new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
			.in(SecurityScheme.In.HEADER)
			.name("Refresh-Token");

		// 보안 요구 사항 설정
		SecurityRequirement securityRequirement = new SecurityRequirement()
			.addList("accessTokenAuth")
			.addList("refreshTokenAuth");

		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes("accessTokenAuth", accessTokenScheme)
				.addSecuritySchemes("refreshTokenAuth", refreshTokenScheme))
			.security(Arrays.asList(securityRequirement))
			.info(new Info()
				.title("DailySwitter")
				.description("하루 일상을 같이 공유 해봐요.")
				.version("1.0"));
	}
}
