package com.tek271.javaperf.http;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class HttpRunnerTest {

	@Disabled
	@Test
	void testHttpRunner() throws InterruptedException {
		new HttpRunner().warmupCount(3).runCount(1000).runAndPrint(true);

		TimeUnit.SECONDS.sleep(20);
	}
}