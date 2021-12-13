package com.bitbuy.interview.scott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.interview.scott.entity.User;
import com.bitbuy.interview.scott.repository.IUserRepository;
import com.bitbuy.interview.scott.request.CredentialRequest;
import com.bitbuy.interview.scott.response.CredentialResponse;
import com.bitbuy.interview.scott.security.MyUserDetailsService;
import com.bitbuy.interview.scott.translator.UserCredentialTranslator;
import com.bitbuy.interview.scott.util.ErrorResponseBuilder;
import com.bitbuy.interview.scott.util.JwtUtility;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserOnboardingController {
	@Autowired
	IUserRepository userRepository;

	@Autowired
	UserCredentialTranslator userCredentialTranslator;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtUtility jwtUtility;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ErrorResponseBuilder errorResponseBuilder;

	@PostMapping("/register")
	public ResponseEntity<CredentialResponse> createUser(@RequestBody CredentialRequest userCredential) {

		try {
			User user = userRepository.findByUsername(userCredential.getUsername());
			if (user != null) {
				String errorMsg = "User is already existed. Please try login directly";
				return new ResponseEntity<>(CredentialResponse.builder()
						.error(errorResponseBuilder.toError(errorMsg, 400))
						.build(), HttpStatus.BAD_REQUEST);
			}
			userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
			user = userCredentialTranslator.requestToEntity(userCredential);
			User createdUser = userRepository.save(user);
			final String token = jwtUtility.generateToken(createdUser);
			CredentialResponse response = userCredentialTranslator.entityToResponse(user);
			response.setJwt(token);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Exception found!", e);
			String errorMsg = "Unknown error, check log for details";
			return new ResponseEntity<>(CredentialResponse.builder()
					.error(errorResponseBuilder.toError(errorMsg, 500))
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<CredentialResponse> loginUser(@RequestBody CredentialRequest userCredential) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUsername(),
					userCredential.getPassword()));
		} catch (InternalAuthenticationServiceException e) {
			log.error("Username or password is in correct", e);
			String errorMsg = "Username or password is in correct";
			return new ResponseEntity<>(CredentialResponse.builder()
					.error(errorResponseBuilder.toError(errorMsg, 403))
					.build(), HttpStatus.UNAUTHORIZED);
		}

		try {
			User user = userRepository.findByUsername(userCredential.getUsername());
			final String token = jwtUtility.generateToken(user);
			CredentialResponse response = userCredentialTranslator.entityToResponse(user);
			response.setJwt(token);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Exception found!", e);
			String errorMsg = "Unknown error, check log for details";
			return new ResponseEntity<>(CredentialResponse.builder()
					.error(errorResponseBuilder.toError(errorMsg, 500))
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
