package com.tek271.javaperf.json;

import com.tek271.javaperf.metrics.CallMonitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonRunnerTest {

	@Test
	void runJsonParsingPerf() {
		JsonRunner jsonRunner = new JsonRunner();
		List<CallMonitor> list = jsonRunner.run();
		list.forEach(cm -> System.out.println(cm));
	}

}