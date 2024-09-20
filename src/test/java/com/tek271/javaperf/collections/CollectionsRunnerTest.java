package com.tek271.javaperf.collections;

import org.junit.jupiter.api.Test;

class CollectionsRunnerTest {

	@Test
	void runCollections() {
		new CollectionsRunner().warmupCount(10).runCount(2000).runAndPrint();
	}
}