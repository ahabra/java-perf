package com.tek271.javaperf.http;


import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.apache.commons.lang3.StringUtils.remove;

public class HttpCaller {
	private static final String URL = "https://example.com/";
	private static final URI URI = toUri(URL);
	private final HttpClient client = HttpClient.newHttpClient();

	public String callWithNewClient() {
		return call(HttpClient.newHttpClient());
	}

	public String callReuseClient() {
		return call(client);
	}

	private String call(HttpClient client) {
		HttpRequest request = HttpRequest.newBuilder().uri(URI).GET().build();
		HttpResponse<String> response = send(client, request);
		if (response.statusCode() == 200) {
			return joinLines(response.body());
		}
		throw new RuntimeException(URL + " call failed with " + response.statusCode());
	}

	private static URI toUri(String url) {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private static HttpResponse<String> send(HttpClient client, HttpRequest request) {
		try {
			return client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static String joinLines(String text) {
		return remove(remove(text, '\n'), '\r');
	}

}
