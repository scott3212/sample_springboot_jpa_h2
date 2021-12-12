package com.bitbuy.test.scott.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.test.scott.entity.User;
import com.bitbuy.test.scott.repository.IUserRepository;
import com.bitbuy.test.scott.request.UserRequest;
import com.bitbuy.test.scott.response.UserResponse;
import com.bitbuy.test.scott.translator.UserTranslator;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
	@Autowired
	IUserRepository userRepository;

	@Autowired
	UserTranslator userTranslator;

	@GetMapping("/{uuid}")
	public ResponseEntity<UserResponse> getUserInfo(@PathVariable String uuid) {
		try {
			User user = userRepository.findByUUID(UUID.fromString(uuid));
			return new ResponseEntity<>(userTranslator.entityToResponse(user), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Exception found!", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{uuid}") // Shouldn't it be PUT?
	public ResponseEntity<UserResponse> updateUserInfo(@PathVariable String uuid, @RequestBody UserRequest request) {
		User user = userRepository.findByUUID(UUID.fromString(uuid));
		if (user == null) {
			log.error("The user can't be found: " + uuid);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user = userRepository.save(user);
		return new ResponseEntity<>(userTranslator.entityToResponse(user), HttpStatus.CREATED);
	}
}
