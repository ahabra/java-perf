package com.tek271.javaperf.http;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpCaller {
	private static final String URL = "https://example.com/";
	private static final URI URI = toUri(URL);

	public HttpResponse<String> buildAndCall() {
		HttpRequest request = HttpRequest.newBuilder().uri(URI).GET().build();
		HttpClient client = HttpClient.newHttpClient();
		return send(client, request);
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


}
