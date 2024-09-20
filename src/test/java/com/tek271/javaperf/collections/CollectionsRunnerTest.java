package com.tek271.javaperf.collections;

import com.tek271.javaperf.metrics.CallMonitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsRunnerTest {

	@Test
	void runCollections() {
		CollectionsRunner runner = new CollectionsRunner();
		List<CallMonitor> list = runner.run();
		runner.printMonitors(list);
	}
}