package com.tek271.javaperf.reflection;

import com.tek271.javaperf.model.Book;
import com.tek271.javaperf.utils.Prove;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.tek271.javaperf.model.Book.HITCHHIKER;

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
		Prove.equal(HITCHHIKER.title, title, "title");
		Prove.equal(HITCHHIKER.pages, pages, "pages");
	}

	public void callMethod() {
		long value;
		try {
			value = (Long) CALC_VALUE_METHOD.invoke(HITCHHIKER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Prove.equal(736_064L, value, "value");
	}

}
