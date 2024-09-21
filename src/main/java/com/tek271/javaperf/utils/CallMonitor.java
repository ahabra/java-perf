package com.tek271.javaperf.utils;

public class CallMonitor {
	private static final long MILLION = 1000_000;
	private String description;
	private int count = 1;
	private long runDuration;
	private Runnable runnable;

	private CallMonitor() {}

	public static CallMonitor of(String description) {
		CallMonitor callMonitor = new CallMonitor();
		callMonitor.description = description;
		return callMonitor;
	}

	public static CallMonitor of() {
		return of("");
	}

	public CallMonitor repeat(int count) {
		this.count = count;
		return this;
	}

	public CallMonitor task(Runnable runnable) {
		this.runnable = runnable;
		return this;
	}

	public CallMonitor run() {
		runDuration = 0;
		long t0 = System.nanoTime();
		try {
			for (int i=0; i < count; i++) {
				runnable.run();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		runDuration = System.nanoTime() - t0;
		return this;
	}

	public long getRunDurationNanos() {
		return runDuration;
	}

	public long getRunDurationMillis() {
		long millis = 0;
		long remainder = runDuration % MILLION;
		if (remainder > MILLION/2) {
			millis++;
		}
		if (runDuration > MILLION) {
			millis += runDuration / MILLION;
		}
		return millis;
	}


	@Override
	public String toString() {
		long millis = getRunDurationMillis();
		if (millis > 0) {
			return String.format("%s: count=%,d, duration=%,d nanos=%,d millis", description, count, runDuration, millis);
		} else {
			return String.format("%s: count=%,d, duration=%,d nanos", description, count, runDuration);
		}
	}

}
