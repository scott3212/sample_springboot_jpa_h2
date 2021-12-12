package com.bitbuy.test.scott.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.test.scott.entity.User;
import com.bitbuy.test.scott.repository.IUserRepository;
import com.bitbuy.test.scott.request.CredentialRequest;
import com.bitbuy.test.scott.response.CredentialResponse;
import com.bitbuy.test.scott.translator.UserCredentialTranslator;

@RestController
@RequestMapping("/api")
public class UserOnboardingController {
	private static final Logger log = LoggerFactory.getLogger(UserOnboardingController.class);
	@Autowired
	IUserRepository userRepository;

	@Autowired
	UserCredentialTranslator userCredentialTranslator;

	@PostMapping("/register")
	public ResponseEntity<CredentialResponse> createUser(@RequestBody CredentialRequest userCredential) {
		try {
			User user = userCredentialTranslator.requestToEntity(userCredential);
			User createdUser = userRepository.save(user);
			return new ResponseEntity<>(userCredentialTranslator.entityToResponse(createdUser), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Exception found!", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<CredentialResponse> logineUser(@RequestBody CredentialRequest userCredential) {
		try {
			User user = userRepository.findByUsername(userCredential.getUsername());
			if (user == null) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			if (user.getPassword().equals(userCredential.getPassword())) {
				return new ResponseEntity<>(userCredentialTranslator.entityToResponse(user), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			log.error("Exception found!", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
