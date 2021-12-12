package com.bitbuy.test.scott.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class ErrorResponse {
	private String errorMsg;
	private int errorCode;
}
