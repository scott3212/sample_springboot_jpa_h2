package com.bitbuy.test.scott.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class UserResponse {
	private UUID uuid;
	private String name;
	private String email;
	private String phone;
}
