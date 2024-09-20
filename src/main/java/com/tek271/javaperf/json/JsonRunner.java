package com.tek271.javaperf.json;

import com.tek271.javaperf.metrics.CallMonitor;
import com.tek271.javaperf.metrics.PerformanceRunner;

public class JsonRunner extends PerformanceRunner {

	public JsonRunner() {
		addCallMonitor(CallMonitor.of("JacksonMapper.parse").task(new JacksonMapper()::parse));
		addCallMonitor(CallMonitor.of("JacksonFieldParser.parse").task(new JacksonFieldParser()::parse));
	}

}
