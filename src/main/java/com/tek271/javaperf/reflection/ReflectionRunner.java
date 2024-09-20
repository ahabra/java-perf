package com.tek271.javaperf.reflection;

import com.tek271.javaperf.metrics.CallMonitor;
import com.tek271.javaperf.metrics.PerformanceRunner;

import java.util.ArrayList;
import java.util.List;

public class ReflectionRunner extends PerformanceRunner {
	private static final int WARMUP_COUNT = 100;
	private static final int COUNT = 1000_000;
	private final ApacheReflect apacheReflect = new ApacheReflect();
	private final CallMonitor apacheReadField = CallMonitor.of("ApacheReflect.readFields").task(apacheReflect::readFields);
	private final CallMonitor apacheCallMethod = CallMonitor.of("ApacheReflect.callMethod").task(apacheReflect::callMethod);

	private final ClassicReflect classicReflect = new ClassicReflect();
	private final CallMonitor classicReadField = CallMonitor.of("ClassicReflect.readFields").task(classicReflect::readFields);
	private final CallMonitor classicCallMethod = CallMonitor.of("ClassicReflect.callMethod").task(classicReflect::callMethod);

	private final HandleInvoke handleInvoke = new HandleInvoke();
	private final CallMonitor invokeField = CallMonitor.of("HandleInvoke.readFields").task(handleInvoke::readFields);
	private final CallMonitor invokeMethod = CallMonitor.of("HandleInvoke.callMethod").task(handleInvoke::callMethod);


	private void warmup() {
		apacheReadField.repeat(WARMUP_COUNT).run();
		apacheCallMethod.repeat(WARMUP_COUNT).run();
		classicReadField.repeat(WARMUP_COUNT).run();
		classicCallMethod.repeat(WARMUP_COUNT).run();
		invokeField.repeat(WARMUP_COUNT).run();
		invokeMethod.repeat(WARMUP_COUNT).run();
	}

	@Override
	public List<CallMonitor> run() {
		warmup();
		List<CallMonitor> list = new ArrayList<>();
		list.add(apacheReadField.repeat(COUNT).run());
		list.add(apacheCallMethod.repeat(COUNT).run());
		list.add(classicReadField.repeat(COUNT).run());
		list.add(classicCallMethod.repeat(COUNT).run());
		list.add(invokeField.repeat(COUNT).run());
		list.add(invokeMethod.repeat(COUNT).run());
		return list;
	}

}
