package com.tek271.javaperf.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randoms {
	private static final Random RANDOM = new Random();
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final SecureRandom STRONG_SECURE_RANDOM = getStrongSecure();

	public double mathRandom() {
		int a = (int) (Math.random() * Integer.MAX_VALUE);
		double b = Math.random();
		return a + b;
	}

	public double strictMathRandom() {
		int a = (int) (StrictMath.random() * Integer.MAX_VALUE);
		double b = Math.random();
		return a + b;
	}

	public double randomClass() {
		int a = RANDOM.nextInt();
		double b = RANDOM.nextDouble();
		return a + b;
	}

	public double threadLocalRandom() {
		int a = ThreadLocalRandom.current().nextInt();
		double b = ThreadLocalRandom.current().nextDouble();
		return a + b;
	}

	public double secureRandom() {
		int a = SECURE_RANDOM.nextInt();
		double b = SECURE_RANDOM.nextDouble();
		return a + b;
	}

	public double strongSecureRandom() {
		int a = STRONG_SECURE_RANDOM.nextInt();
		double b = STRONG_SECURE_RANDOM.nextDouble();
		return a + b;
	}

	private static SecureRandom getStrongSecure() {
		try {
			return SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
