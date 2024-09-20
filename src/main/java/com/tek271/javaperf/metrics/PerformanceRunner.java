package com.tek271.javaperf.metrics;

import java.util.ArrayList;
import java.util.List;

public abstract class PerformanceRunner {
	private final List<CallMonitor> callMonitors = new ArrayList<>();

	public abstract int getWarmupCount();
	public abstract int getRunCount();

	public void addCallMonitor(CallMonitor callMonitor) {
		callMonitors.add(callMonitor);
	}

	public void printMonitors() {
		callMonitors.forEach(System.out::println);
	}

	private void run(int count) {
		callMonitors.forEach(cm -> cm.repeat(count).run());
	}

	public List<CallMonitor> run() {
		run(getWarmupCount());
		run(getRunCount());
		return callMonitors;
	}

	public void runAndPrint() {
		run();
		printMonitors();
	}

}
