package com.tek271.javaperf.text;

import com.tek271.javaperf.utils.CallMonitor;
import com.tek271.javaperf.utils.PerformanceRunner;

public class TextRunner extends PerformanceRunner {

	public TextRunner() {
		Replacer replacer = new Replacer();

		addCallMonitor(CallMonitor.of("Replacer:withStringReplaceAll").task(replacer::withStringReplaceAll));
		addCallMonitor(CallMonitor.of("Replacer:withApacheCommons").task(replacer::withApacheCommons));
		addCallMonitor(CallMonitor.of("Replacer:withStringReplaceAllRegEx").task(replacer::withStringReplaceAllRegEx));
	}

}
