package com.tek271.javaperf.hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashRunnerTest {

	@Test
	void testSha256() {
		new HashRunner().warmupCount(100).runCount(1000).runAndPrint(true);
	}

	@Test
	void testHashes() {
		Sha256 sha256 = new Sha256();
		String jdkSha = sha256.sha256_jdk();
		assertEquals(jdkSha, sha256.sha256_guava());
		assertEquals(jdkSha, sha256.sha256_apacheCodec());

		String jdkSha3 = sha256.sha3_256_jdk();
		assertEquals(jdkSha3, sha256.sha3_256_apacheCodec());
//		assertEquals(jdkSha3, sha256.sha3_256_keccak());

	}

}