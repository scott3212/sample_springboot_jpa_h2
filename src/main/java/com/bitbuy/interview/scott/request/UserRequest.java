package com.bitbuy.interview.scott.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class UserRequest {
	private String name;
	private String email;
	private String phone;
}
