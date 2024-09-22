package com.tek271.javaperf.random;

import com.tek271.javaperf.utils.CallMonitor;
import com.tek271.javaperf.utils.PerformanceRunner;

public class RandomRunner extends PerformanceRunner {

	public RandomRunner() {
		Randoms randoms = new Randoms();

		addCallMonitor(CallMonitor.of("Randoms.mathRandom").task(randoms::mathRandom));
		addCallMonitor(CallMonitor.of("Randoms.strictMathRandom").task(randoms::strictMathRandom));
		addCallMonitor(CallMonitor.of("Randoms.randomClass").task(randoms::randomClass));
		addCallMonitor(CallMonitor.of("Randoms.threadLocalRandom").task(randoms::threadLocalRandom));
		addCallMonitor(CallMonitor.of("Randoms.secureRandom").task(randoms::secureRandom));
		addCallMonitor(CallMonitor.of("Randoms.strongSecureRandom").task(randoms::strongSecureRandom));
	}

}
