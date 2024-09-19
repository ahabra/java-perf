package com.tek271.javaperf.reflection;

import com.google.common.base.Verify;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.tek271.javaperf.reflection.Book.HITCHHIKER;

public class ClassicReflect {
	private static final Field TITLE_FIELD = getField(Book.class, "title");
	private static final Field PAGES_FIELD = getField(Book.class, "pages");
	private static final Method CALC_VALUE_METHOD = getMethod(Book.class, "calcValue");

	private static Field getField(Class<?> cls, String fieldName) {
		try {
			return cls.getField(fieldName);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	private static Method getMethod(Class<?> cls, String methodName) {
		try {
			return cls.getMethod(methodName);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public void readFields() {
		String title;
		int pages;

		try {
			title = (String) TITLE_FIELD.get(HITCHHIKER);
			pages = PAGES_FIELD.getInt(HITCHHIKER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Verify.verify(HITCHHIKER.title.equals(title), "Title is different");
		Verify.verify(HITCHHIKER.pages == pages, "pages is different");
	}

	public void callMethod() {
		long value;
		try {
			value = (Long) CALC_VALUE_METHOD.invoke(HITCHHIKER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Verify.verify(value == 736_064, "Value is " + value);
	}

}
