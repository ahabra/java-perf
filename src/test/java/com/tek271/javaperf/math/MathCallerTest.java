package com.tek271.javaperf.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.tek271.javaperf.utils.PrintUtils.printlnDouble;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class MathCallerTest {

	@Test
	void compareResults() {
		MathCaller caller = new MathCaller();
		double classic = caller.multiplyAndAdd_classic();
		double fma = caller.multiplyAndAdd_fma();

		assertEquals(classic, fma);
	}

	@Test
	void compareClassicWithFma() {
		double a = 1.000_000_119_209_29;
		double b = 53_400_708;
		double c = -b;

//		double a = 1.000_01;
//		double b = 100_000_000;
//		double c = -b;

		double bigd = valueOf(a).multiply(valueOf(b)).add(valueOf(c)).doubleValue();
		double classic = a * b +  c;
		double fma = Math.fma(a, b, c);

		printlnDouble("bigDecimal ", bigd);
		printlnDouble("classic    ", classic);
		printlnDouble("fma        ", fma);

		System.out.println();
		printlnDouble("classic error", bigd - classic);
		printlnDouble("fma error    ", bigd - fma);

		// the strange behavior
		assertNotEquals(fma, classic);
	}

}