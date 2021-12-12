package com.bitbuy.test.scott.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.test.scott.entity.User;
import com.bitbuy.test.scott.repository.IUserRepository;
import com.bitbuy.test.scott.response.UserResponse;
import com.bitbuy.test.scott.translator.UserTranslator;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserOnboardingController.class);

	@Autowired
	IUserRepository userRepository;

	@Autowired
	UserTranslator userTranslator;

	@GetMapping("/{uuid}")
	public ResponseEntity<UserResponse> createUser(@PathVariable String uuid) {
		try {
			User user = userRepository.findByUUID(UUID.fromString(uuid));
			return new ResponseEntity<>(userTranslator.entityToResponse(user), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Exception found!", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
