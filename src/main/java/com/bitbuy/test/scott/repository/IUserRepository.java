package com.bitbuy.test.scott.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bitbuy.test.scott.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM users u where u.username=?1")
	User findByUsername(String username);
}
