package com.tek271.javaperf.json;

import com.fasterxml.jackson.core.JsonParser;
import com.tek271.javaperf.model.Author;
import com.tek271.javaperf.model.Book;
import com.tek271.javaperf.utils.FileTools;
import com.tek271.javaperf.utils.Prove;

import java.io.IOException;

import static com.tek271.javaperf.json.JsonUtils.*;
import static com.tek271.javaperf.model.Book.HITCHHIKER;

public class JacksonFieldParser {
	private static final String TEXT = FileTools.readResource("json/hitchhiker.json");

	public Book parse() {
		JsonParser parser = null;
		Book book;
		try {
			parser = createParser(TEXT);
			book = runParser(parser);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeParser(parser);
		}

		Prove.equal(HITCHHIKER, book, "book");
		return book;
	}

	private Book runParser(JsonParser parser) throws IOException {
		Book book = new Book();
		book.author = new Author();
		setObjectFields(book, parser, this::setBookFieldNoException);
		return book;
	}

	private void setBookField(Book book, JsonParser parser) throws IOException {
		String fieldName = parser.currentName();
		if (fieldName == null) {
			skipCurrentNode(parser);
			return;
		}

		if (fieldName.equals("title")) {
			book.title = nextValueAsString(parser);
		} else if (fieldName.equals("pages")) {
			book.pages = nextValueAsInt(parser);
		} else if (fieldName.equals("author")) {
			setObjectFields(book.author, parser, this::setAuthorFieldNoExceptions);
		} else {
			skipCurrentNode(parser);
		}
	}

	private void setBookFieldNoException(Book book, JsonParser parser) {
		try {
			setBookField(book, parser);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void setAuthorField(Author author, JsonParser parser) throws IOException  {
		String fieldName = parser.currentName();
		if (fieldName == null) {
			skipCurrentNode(parser);
			return;
		}

		if (fieldName.equals("firstName")) {
			author.firstName = nextValueAsString(parser);
		} else if (fieldName.equals("lastName")) {
			author.lastName = nextValueAsString(parser);
		} else if (fieldName.equals("country")) {
			author.country = nextValueAsString(parser);
		} else {
			skipCurrentNode(parser);
		}
	}

	private void setAuthorFieldNoExceptions(Author author, JsonParser parser) {
		try {
			setAuthorField(author, parser);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}