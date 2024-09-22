package com.tek271.javaperf.random;

import org.junit.jupiter.api.Test;

class RandomRunnerTest {

	@Test
	void runRandoms() {
		new RandomRunner().warmupCount(1000).runCount(1000_000).runAndPrint();
	}
}