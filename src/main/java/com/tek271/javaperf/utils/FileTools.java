package com.tek271.javaperf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileTools {

	public static String readResource(String resourcePath) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try(InputStream stream = classLoader.getResourceAsStream(resourcePath)) {
			if (stream == null) {
				System.err.println(resourcePath + " not found");
				return null;
			}
			return inputStreamToString(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String inputStreamToString(InputStream stream) {
		if (stream == null) return null;

		byte[] bytes;
		try {
			bytes = stream.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new String(bytes, StandardCharsets.UTF_8);
	}

}
