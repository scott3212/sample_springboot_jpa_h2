package com.bitbuy.interview.scott.util;

import org.springframework.stereotype.Component;

import com.bitbuy.interview.scott.response.ErrorResponse;

@Component
public class ErrorResponseBuilder {
	public ErrorResponse toError(String msg, int httpErrorCode) {
		return ErrorResponse.builder()
				.errorMsg(msg)
				.errorCode(httpErrorCode)
				.build();
	}
}
