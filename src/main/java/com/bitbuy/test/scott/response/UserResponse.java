package com.bitbuy.test.scott.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class UserResponse {
	private UUID uuid;
	private String name;
	private String email;
	private String phone;
	private ErrorResponse error;
}
