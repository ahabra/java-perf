package com.tek271.javaperf.metrics;

import java.util.List;

public abstract class PerformanceRunner {
	public abstract List<CallMonitor> run();

	public void printMonitors(List<CallMonitor> list) {
		list.forEach(System.out::println);
	}

}
