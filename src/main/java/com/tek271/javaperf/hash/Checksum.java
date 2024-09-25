package com.tek271.javaperf.hash;

import org.apache.commons.codec.digest.XXHash32;

import java.nio.charset.StandardCharsets;

public class Checksum {
	private static final String TEXT = "Hello World 1234";

	public long xxHash32() {
		byte[] bytes = TEXT.getBytes(StandardCharsets.UTF_8);
		XXHash32 xx = new XXHash32();
		xx.update(bytes, 0, bytes.length);
		return xx.getValue();
	}

}
