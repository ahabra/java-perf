package com.tek271.javaperf.reflection;

import com.google.common.base.Verify;

import static com.tek271.javaperf.reflection.Book.HITCHHIKER;

public class JdkReflect {

	public void readFields() {
		String title;
		int pages;

		try {
			title = (String) Book.class.getField("title").get(HITCHHIKER);
			pages = Book.class.getField("pages").getInt(HITCHHIKER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Verify.verify(HITCHHIKER.title.equals(title), "Title is different");
		Verify.verify(HITCHHIKER.pages == pages, "pages is different");
	}

	public void callMethod() {
		long value;
		try {
			value = (Long) Book.class.getMethod("calcValue").invoke(HITCHHIKER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Verify.verify(value == 736_064, "Value is " + value);
	}

}
