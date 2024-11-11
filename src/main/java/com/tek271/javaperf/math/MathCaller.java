package com.tek271.javaperf.math;


import java.math.BigDecimal;

public class MathCaller {
	private static final int SIZE = 100;
	private static final double SMALL_VALUE = 1.00456;
	private static final double BIG_VALUE = 42_000_000;
	private static final double ACCUM_START = 0;

	private static final double[] AR1 = createArray(SMALL_VALUE);
	private static final double[] AR2 = createArray(BIG_VALUE);


	private static double[] createArray(double value) {
		double[] ar = new double[SIZE];
		for (int i=0; i<SIZE; i++) {
			ar[i] = value;
		}
		return ar;
	}

	public double multiplyAndAdd_classic() {
		double accum = ACCUM_START;
		for (int i=0; i<SIZE; i++) {
			accum += AR1[i] * AR2[i];
		}
		return accum;
	}

	public double multiplyAndAdd_fma() {
		double accum = ACCUM_START;
		for (int i=0; i<SIZE; i++) {
			accum = Math.fma(AR1[i], AR2[i], accum);
		}
		return accum;
	}

	public BigDecimal multiplyAndAdd_bigDecimal() {
		BigDecimal accum = BigDecimal.valueOf(ACCUM_START);
		for (int i=0; i<SIZE; i++) {
			BigDecimal a = BigDecimal.valueOf(AR1[i]);
			BigDecimal b = BigDecimal.valueOf(AR2[i]);
			accum = a.multiply(b).add(accum);
		}
		return accum;
	}

}
