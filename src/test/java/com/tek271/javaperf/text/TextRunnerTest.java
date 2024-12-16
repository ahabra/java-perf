package com.tek271.javaperf.text;

import org.junit.jupiter.api.Test;

class TextRunnerTest {

	@Test
	void testTextRunner() {
		new TextRunner(false).warmupCount(100).runCount(500_000).runAndPrint(true);
		new TextRunner(true).warmupCount(100).runCount(50_000).runAndPrint(true);
	}

}