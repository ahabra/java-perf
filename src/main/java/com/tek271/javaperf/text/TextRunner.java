package com.tek271.javaperf.text;

import com.tek271.javaperf.utils.CallMonitor;
import com.tek271.javaperf.utils.PerformanceRunner;

public class TextRunner extends PerformanceRunner {

	public TextRunner(boolean isReplacer) {
		if (isReplacer) {
			replacer();
		} else {
			stringer();
		}
	}

	private void replacer() {
		Replacer replacer = new Replacer();
		addCallMonitor(CallMonitor.of("Replacer:withStringReplaceAll").task(replacer::withStringReplaceAll));
		addCallMonitor(CallMonitor.of("Replacer:withApacheCommons").task(replacer::withApacheCommons));
		addCallMonitor(CallMonitor.of("Replacer:withStringReplaceAllRegEx").task(replacer::withStringReplaceAllRegEx));
	}

	private void stringer() {
		Stringer stringer = new Stringer();
		addCallMonitor(CallMonitor.of("Stringer:toString_concat").task(stringer::toString_concat));
		addCallMonitor(CallMonitor.of("Stringer:toString_StringBuilder").task(stringer::toString_StringBuilder));
		addCallMonitor(CallMonitor.of("Stringer:toString_reflection").task(stringer::toString_reflection));
		addCallMonitor(CallMonitor.of("Stringer:toString_builder").task(stringer::toString_builder));
	}

}
