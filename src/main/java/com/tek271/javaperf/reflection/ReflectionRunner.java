package com.tek271.javaperf.reflection;

import com.tek271.javaperf.utils.CallMonitor;
import com.tek271.javaperf.utils.PerformanceRunner;

public class ReflectionRunner extends PerformanceRunner {

	public ReflectionRunner() {
		ApacheReflect apacheReflect = new ApacheReflect();
		addCallMonitor(CallMonitor.of("ApacheReflect.readFields").task(apacheReflect::readFields));
		addCallMonitor(CallMonitor.of("ApacheReflect.callMethod").task(apacheReflect::callMethod));

		ClassicReflect classicReflect = new ClassicReflect();
		addCallMonitor(CallMonitor.of("ClassicReflect.readFields").task(classicReflect::readFields));
		addCallMonitor(CallMonitor.of("ClassicReflect.callMethod").task(classicReflect::callMethod));

		HandleInvoke handleInvoke = new HandleInvoke();
		addCallMonitor(CallMonitor.of("HandleInvoke.readFields").task(handleInvoke::readFields));
		addCallMonitor(CallMonitor.of("HandleInvoke.callMethod").task(handleInvoke::callMethod));
	}

}
