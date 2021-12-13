package com.bitbuy.interview.scott.translator;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bitbuy.interview.scott.entity.User;
import com.bitbuy.interview.scott.request.UserRequest;
import com.bitbuy.interview.scott.response.UserResponse;

public class UserTranslatorTest {
	private UserTranslator subject;
	private final String NAME = "Scott";
	private final String EMAIL = "scott@huizhenwu.ca";
	private final String PHONE = "7058855885";
	private final String USERNAME = "scott3212";

	@BeforeEach
	public void setup() {
		subject = new UserTranslator();
	}

	@Test
	public void testTranslateToResponse() {
		UUID uuid = UUID.randomUUID();
		User input = User.builder().name(NAME).email(EMAIL).phone(PHONE).username(USERNAME)
				.password("1234").uuid(uuid).build();

		UserResponse response = subject.entityToResponse(input);

		Assertions.assertThat(response).extracting(
				UserResponse::getEmail,
				UserResponse::getName,
				UserResponse::getPhone,
				UserResponse::getUuid)
				.containsExactly(
						EMAIL,
						NAME,
						PHONE,
						uuid);
	}

	@Test
	public void testTranslateToEntity() {
		UserRequest input = UserRequest.builder().email(EMAIL).name(NAME).phone(PHONE).build();

		User response = subject.requestToEntity(input);

		Assertions.assertThat(response).extracting(
				User::getEmail,
				User::getName,
				User::getPhone)
				.containsExactly(
						EMAIL,
						NAME,
						PHONE);
	}
}
