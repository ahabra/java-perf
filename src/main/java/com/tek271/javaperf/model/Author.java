package com.tek271.javaperf.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Author {
	public static final Author ADAMS = new Author("Douglas", "Adams", "UK");

	public String firstName;
	public String lastName;
	public String country;

	public Author(String firstName, String lastName, String country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
	}

	public Author() {
		this("", "", "");
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + ", " + country;
	}
}
