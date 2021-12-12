package com.bitbuy.test.scott.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class CredentialResponse {
	private UUID uuid;
	private String username;
	private String jwt;
	private ErrorResponse error;
}
