package com.tek271.javaperf.reflection;

import com.tek271.javaperf.metrics.CallMonitor;
import org.junit.jupiter.api.Test;

import java.util.List;

class ReflectionRunnerTest {

	@Test
	void doit() {
		ReflectionRunner runner = new ReflectionRunner();
		List<CallMonitor> list = runner.run();
		runner.printMonitors(list);
	}
}