package com.tek271.javaperf.utils;

import java.math.BigInteger;

public class HexUtils {

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b: bytes) {
			sb.append(Character.forDigit((b >> 4) & 0xF, 16));
			sb.append(Character.forDigit(b & 0xF, 16));
		}
		return sb.toString();
	}

	public static String bytesToHex2(byte[] bytes) {
		return String.format("%064x", new BigInteger(1, bytes));
	}

}
