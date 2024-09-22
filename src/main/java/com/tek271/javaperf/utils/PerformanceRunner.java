package com.tek271.javaperf.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class PerformanceRunner {
	private final List<CallMonitor> callMonitors = new ArrayList<>();
	private int warmupCount = 1;
	private int runCount = 100;

	public PerformanceRunner warmupCount(int warmupCount) {
		this.warmupCount = warmupCount;
		return this;
	}

	public PerformanceRunner runCount(int runCount) {
		this.runCount = runCount;
		return this;
	}

	public void addCallMonitor(CallMonitor callMonitor) {
		callMonitors.add(callMonitor);
	}

	public void printMonitors(boolean isSorted) {
		if (isSorted) {
			callMonitors.stream()
					.sorted(Comparator.comparing(CallMonitor::getRunDurationNanos))
					.forEach(System.out::println);
		} else {
			callMonitors.forEach(System.out::println);
		}
	}

	private void run(int count) {
		callMonitors.forEach(cm -> cm.repeat(count).run());
	}

	public List<CallMonitor> run() {
		run(warmupCount);
		run(runCount);
		return callMonitors;
	}

	public void runAndPrint(boolean isSorted) {
		run();
		printMonitors(isSorted);
	}

	public void runAndPrint() {
		runAndPrint(false);
	}
}
