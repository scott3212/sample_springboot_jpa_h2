package com.bitbuy.test.scott.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class UserRequest {
	private UUID uuid;
	private String name;
	private String email;
	private String phone;
}
