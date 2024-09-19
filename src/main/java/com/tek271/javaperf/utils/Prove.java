package com.tek271.javaperf.utils;

import com.google.common.base.Verify;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Prove {

	public static void equal(int expected, int actual, String msg) {
		Verify.verify(expected == actual, buildMsg(expected, actual, msg));
	}

	public static void equal(long expected, long actual, String msg) {
		Verify.verify(expected == actual, buildMsg(expected, actual, msg));
	}

	public static void equal(Object expected, Object actual, String msg) {
		Verify.verify(Objects.equals(expected, actual), buildMsg(expected, actual, msg));
	}

	private static String buildMsg(Object expected, Object actual, String msg) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(msg)) {
			sb.append(msg).append(": ");
		}
		sb.append("expected ").append(expected);
		sb.append(" but found ").append(actual);
		return sb.toString();
	}

}
