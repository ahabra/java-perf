package com.tek271.javaperf.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static com.tek271.javaperf.model.Author.ADAMS;

public class Book {
	public static final Book HITCHHIKER = new Book("The Hitchhiker's Guide to the Galaxy", 224, ADAMS);

	public String title;
	public int pages;
	public Author author;

	public Book() {}

	public Book(String title, int pages, Author author) {
		this.title = title;
		this.pages = pages;
		this.author = author;
	}

	public long calcValue() {
		return (long) title.chars().sum() * pages;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
