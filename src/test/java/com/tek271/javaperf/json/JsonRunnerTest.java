package com.tek271.javaperf.json;

import org.junit.jupiter.api.Test;

class JsonRunnerTest {

	@Test
	void runJsonParsingPerf() {
		new JsonRunner().runAndPrint();
	}

}