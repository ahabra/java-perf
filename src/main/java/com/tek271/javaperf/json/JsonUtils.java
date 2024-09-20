package com.tek271.javaperf.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.util.function.BiConsumer;

public class JsonUtils {

	public static JsonParser createParser(String json) {
		try {
			return new JsonFactory().createParser(json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeParser(JsonParser parser) {
		if (parser == null) {
			return;
		}
		try {
			parser.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void skipCurrentNode(JsonParser parser) throws IOException {
		JsonToken token = parser.nextToken();
		if (token.isScalarValue() || token.isStructEnd()) {
			return;
		}

		if (token.isStructStart()) {
			skipCurrentNode(parser);
		}
	}

	public static String nextValueAsString(JsonParser parser) throws IOException {
		parser.nextToken();
		return parser.getValueAsString();
	}

	public static int nextValueAsInt(JsonParser parser) throws IOException {
		parser.nextToken();
		return parser.getIntValue();
	}

	public static <T> void setObjectFields(T object, JsonParser parser, BiConsumer<T, JsonParser> consumer) throws IOException {
		parser.nextToken();
		while (true) {
			JsonToken token = parser.nextToken();
			if (token == null || token == JsonToken.END_OBJECT) break;
			consumer.accept(object, parser);
		}
	}

}