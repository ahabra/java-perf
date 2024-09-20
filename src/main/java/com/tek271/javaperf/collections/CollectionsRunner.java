package com.tek271.javaperf.collections;

import com.tek271.javaperf.metrics.CallMonitor;
import com.tek271.javaperf.metrics.PerformanceRunner;

public class CollectionsRunner extends PerformanceRunner {

	public CollectionsRunner() {
		Lists lists = new Lists();
		addCallMonitor(CallMonitor.of("Lists.classic").task(lists::classic));
		addCallMonitor(CallMonitor.of("Lists.primitives").task(lists::primitives));
	}

}
