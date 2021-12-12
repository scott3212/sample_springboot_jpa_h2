package com.bitbuy.test.scott.translator;

import org.springframework.stereotype.Component;

import com.bitbuy.test.scott.entity.User;
import com.bitbuy.test.scott.request.CredentialRequest;
import com.bitbuy.test.scott.response.CredentialResponse;

@Component
public class UserCredentialTranslator implements ObjectTranslator<User, CredentialRequest, CredentialResponse> {

	@Override
	public CredentialResponse entityToResponse(User entity) {
		return CredentialResponse.builder()
				.username(entity.getUsername())
				.uuid(entity.getUuid())
				.build();
	}

	@Override
	public User requestToEntity(CredentialRequest request) {
		return User.builder()
				.username(request.getUsername())
				.password(request.getPassword())
				.build();
	}
}
