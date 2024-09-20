package com.tek271.javaperf.reflection;

import org.junit.jupiter.api.Test;

class ReflectionRunnerTest {

	@Test
	void doit() {
		new ReflectionRunner().warmupCount(100).runCount(1000_000).runAndPrint();
	}

}