package com.bitbuy.interview.scott.security;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bitbuy.interview.scott.entity.User;
import com.bitbuy.interview.scott.repository.IUserRepository;
import com.bitbuy.interview.scott.util.JwtUtility;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
	private final static String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	IUserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader(HEADER_AUTH);
		String requestUUID = null;
		try {
			requestUUID = request.getRequestURI().substring(request.getRequestURI().indexOf("users") + 6)
					.substring(0, 36);
		} catch (IndexOutOfBoundsException e) {
			log.info("The request URI " + request.getRequestURI() + "doesn't contains user's UUID");
		}
		if (isBearerToken(authHeader)) {
			String jwt = authHeader.substring(7);
			String uuid = jwtUtility.getUuidFromToken(jwt);
			if (uuid != null
					&& SecurityContextHolder.getContext().getAuthentication() == null
					&& (requestUUID == null || uuid.equals(requestUUID))) {
				User user = userRepository.findByUUID(UUID.fromString(uuid));
				if (jwtUtility.validateToken(jwt, user)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							user, null, user.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
							.buildDetails(
									request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	private boolean isBearerToken(String authHeader) {
		return authHeader != null && authHeader.startsWith("Bearer ");
	}

}
