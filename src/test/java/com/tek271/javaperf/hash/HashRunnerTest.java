package com.tek271.javaperf.hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashRunnerTest {

	@Test
	void testSha256() {
		new HashRunner().warmupCount(100).runCount(1000).runAndPrint(true);
	}
}