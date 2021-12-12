package com.bitbuy.test.scott.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class CredentialRequest {
	private String username;
	private String password;
}
