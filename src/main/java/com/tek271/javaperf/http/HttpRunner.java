package com.tek271.javaperf.http;


import com.tek271.javaperf.utils.CallMonitor;
import com.tek271.javaperf.utils.PerformanceRunner;
import org.apache.commons.lang3.StringUtils;

public class HttpRunner extends PerformanceRunner {

	public HttpRunner() {
		HttpCaller caller = new HttpCaller();

//		System.gc();
		addCallMonitor(CallMonitor.of("HttpCaller:callWithNewClient").task(()-> print(caller.callWithNewClient())));
		addCallMonitor(CallMonitor.of("HttpCaller:callReuseClient").task(()-> print(caller.callReuseClient())));
	}

	private static void print(String text) {
		System.out.println(StringUtils.left(text, 60) + " ...");
	}

}
