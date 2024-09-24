package com.tek271.javaperf.hash;

import com.tek271.javaperf.utils.CallMonitor;
import com.tek271.javaperf.utils.PerformanceRunner;

public class HashRunner extends PerformanceRunner {

	public HashRunner() {
		Sha256 sha256 = new Sha256();

		addCallMonitor(CallMonitor.of("Sha256:sha256_jdk").task(sha256::sha256_jdk));
		addCallMonitor(CallMonitor.of("Sha256:sha256_guava").task(sha256::sha256_guava));
		addCallMonitor(CallMonitor.of("Sha256:sha256_apacheCodec").task(sha256::sha256_apacheCodec));
		addCallMonitor(CallMonitor.of("Sha256:sha3_256_jdk").task(sha256::sha3_256_jdk));
		addCallMonitor(CallMonitor.of("Sha256:sha3_256_apacheCodec").task(sha256::sha3_256_apacheCodec));
		addCallMonitor(CallMonitor.of("Sha256:sha3_256_keccak").task(sha256::sha3_256_keccak));
	}

}
