package com.tek271.javaperf.json;

import com.tek271.javaperf.metrics.CallMonitor;

import java.util.ArrayList;
import java.util.List;

public class JsonRunner {
	private static final int WARMUP_COUNT = 10;
	private static final int COUNT = 50_000;

	private final JacksonMapper jacksonMapper = new JacksonMapper();
	private final CallMonitor mapperMonitor = CallMonitor.of("JacksonMapper.parse")
			.task(jacksonMapper::parse);

	private final JacksonFieldParser jacksonFieldParser = new JacksonFieldParser();
	private final CallMonitor fieldParserMonitor = CallMonitor.of("JacksonFieldParser.parse")
			.task(jacksonFieldParser::parse);


	private void warmup() {
		mapperMonitor.repeat(WARMUP_COUNT).run();
		fieldParserMonitor.repeat(WARMUP_COUNT).run();
	}

	public List<CallMonitor> run() {
		warmup();
		List<CallMonitor> list = new ArrayList<>();
		list.add(mapperMonitor.repeat(COUNT).run());
		list.add(fieldParserMonitor.repeat(COUNT).run());
		return list;
	}

}
