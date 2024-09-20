package com.tek271.javaperf.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tek271.javaperf.model.Book;
import com.tek271.javaperf.utils.FileTools;
import com.tek271.javaperf.utils.Prove;

import static com.tek271.javaperf.model.Book.HITCHHIKER;

public class JacksonMapper {
	private static final String TEXT = FileTools.readResource("json/hitchhiker.json");

	public Book parse() {
		ObjectMapper objectMapper = new ObjectMapper();
		Book book;
		try {
			book = objectMapper.readValue(TEXT, Book.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		Prove.equal(HITCHHIKER, book, "book");
		return book;
	}

}