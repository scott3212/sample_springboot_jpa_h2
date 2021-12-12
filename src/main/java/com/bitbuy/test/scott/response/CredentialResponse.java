package com.bitbuy.test.scott.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class CredentialResponse {
	private UUID uuid;
	private String username;
	private String jwt;
}
