package com.tek271.javaperf.math;

import org.junit.jupiter.api.Test;

class MathRunnerTest {

	@Test
	void testMathRunner() {
		MathRunner runner = new MathRunner();
		runner.warmupCount(100).runCount(1000_000).runAndPrint(true);
//		runner.printSums();
	}

}