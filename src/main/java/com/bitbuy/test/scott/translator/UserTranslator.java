package com.bitbuy.test.scott.translator;

import org.springframework.stereotype.Component;

import com.bitbuy.test.scott.entity.User;
import com.bitbuy.test.scott.request.UserRequest;
import com.bitbuy.test.scott.response.UserResponse;

@Component
public class UserTranslator implements ObjectTranslator<User, UserRequest, UserResponse> {

	@Override
	public UserResponse entityToResponse(User user) {
		return UserResponse.builder()
				.email(user.getEmail())
				.name(user.getName())
				.phone(user.getPhone())
				.build();
	}

	@Override
	public User requestToEntity(UserRequest request) {
		return User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.phone(request.getPhone())
				.build();
	}
}
