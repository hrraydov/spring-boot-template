package com.hrraydov.security;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ErrorCode {

	Ok(0), Unspecified(-1);

	private final int value;

	private static final Map<Integer, ErrorCode> LOOKUP = Arrays.stream(ErrorCode.values())
			.collect(Collectors.toMap(ErrorCode::getValue, v -> v));

	public int getValue() {
		return value;
	}

	private ErrorCode(int value) {
		this.value = value;
	}

	public static ErrorCode fromValue(int code) {
		return LOOKUP.get(code);
	}
}
