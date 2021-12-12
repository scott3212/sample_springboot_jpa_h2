package com.bitbuy.test.scott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.test.scott.entity.User;
import com.bitbuy.test.scott.repository.IUserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	IUserRepository userRepository;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
