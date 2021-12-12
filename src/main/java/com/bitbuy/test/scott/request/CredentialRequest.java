package com.bitbuy.test.scott.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class CredentialRequest {
	private String username;
	private String password;
}
