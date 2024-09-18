package com.tek271.javaperf.reflection;

public class Book {
	public static final Book HITCHHIKER = new Book("The Hitchhiker's Guide to the Galaxy", 224);

	public String title;
	public int pages;

	public Book(String title, int pages) {
		this.title = title;
		this.pages = pages;
	}

	public long calcValue() {
		return (long) title.chars().sum() * pages;
	}

}
