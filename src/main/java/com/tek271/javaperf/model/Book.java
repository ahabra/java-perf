package com.tek271.javaperf.model;

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

}
