package com.tek271.javaperf.json;

import com.tek271.javaperf.metrics.CallMonitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonRunnerTest {

	@Test
	void runJsonParsingPerf() {
		JsonRunner runner = new JsonRunner();
		List<CallMonitor> list = runner.run();
		runner.printMonitors(list);
	}

}