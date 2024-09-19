package com.tek271.javaperf.model;

public record Author(String firstName, String lastName, String country) {
	public static final Author ADAMS = new Author("Douglas", "Adams", "UK");

}
