package com.tek271.javaperf.math;


import com.tek271.javaperf.utils.CallMonitor;
import com.tek271.javaperf.utils.PerformanceRunner;

import java.math.BigDecimal;

import static com.tek271.javaperf.utils.PrintUtils.printlnDouble;

public class MathRunner extends PerformanceRunner {
	private final double[] sums = {0, 0};

	public MathRunner() {
		MathCaller mathCaller = new MathCaller();
		addCallMonitor(CallMonitor.of("MathCaller:multiplyAndAdd_classic").task(() -> {
			sums[0] += mathCaller.multiplyAndAdd_classic();
		}));

		addCallMonitor(CallMonitor.of("MathCaller:multiplyAndAdd_fma").task(() -> {
			double result = mathCaller.multiplyAndAdd_fma();
			sums[1] = Math.fma(result, 1.0, sums[1]);

		}));

		addCallMonitor(CallMonitor.of("MathCaller:multiplyAndAdd_bigDecimal").task(() -> {
			BigDecimal result = mathCaller.multiplyAndAdd_bigDecimal();
			sums[1] = result.add(BigDecimal.valueOf(sums[1])).doubleValue();
		}));

	}

	public void printSums() {
		printlnDouble("classic", sums[0]);
		printlnDouble("fma    ", sums[1]);
	}

}
