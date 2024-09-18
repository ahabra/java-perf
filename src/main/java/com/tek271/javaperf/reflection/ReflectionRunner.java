package com.tek271.javaperf.reflection;

import com.tek271.javaperf.metrics.CallMonitor;

import java.util.ArrayList;
import java.util.List;

public class ReflectionRunner {
	private static final int WARMUP_COUNT = 100;
	private static final int COUNT = 1000_000;
	private final ApacheReflect apacheReflect = new ApacheReflect();
	private final CallMonitor apacheReadField = CallMonitor.of("ApacheReflect.readFields").task(apacheReflect::readFields);
	private final CallMonitor apacheCallMethod = CallMonitor.of("ApacheReflect.callMethod").task(apacheReflect::callMethod);

	private void warmup() {
		apacheReadField.repeat(WARMUP_COUNT).run();
		apacheCallMethod.repeat(WARMUP_COUNT).run();
	}

	public List<CallMonitor> run() {
		warmup();
		List<CallMonitor> list = new ArrayList<>();
		list.add(apacheReadField.repeat(COUNT).run());
		list.add(apacheCallMethod.repeat(COUNT).run());
		return list;
	}

}
