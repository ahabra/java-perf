package com.tek271.javaperf.collections;

import com.tek271.javaperf.metrics.CallMonitor;
import com.tek271.javaperf.metrics.PerformanceRunner;

import java.util.ArrayList;
import java.util.List;

public class CollectionsRunner extends PerformanceRunner {
	private static final int WARMUP_COUNT = 10;
	private static final int COUNT = 2_000;

	private final Lists lists = new Lists();
	private final CallMonitor classicListMonitor = CallMonitor.of("Lists.classic")
			.task(lists::classic);
	private final CallMonitor primitivesListMonitor = CallMonitor.of("Lists.primitives")
			.task(lists::primitives);

	private void warmup() {
		classicListMonitor.repeat(WARMUP_COUNT).run();
		primitivesListMonitor.repeat(WARMUP_COUNT).run();
	}

	@Override
	public List<CallMonitor> run() {
		warmup();
		List<CallMonitor> list = new ArrayList<>();
		list.add(classicListMonitor.repeat(COUNT).run());
		list.add(primitivesListMonitor.repeat(COUNT).run());
		return list;
	}

}
