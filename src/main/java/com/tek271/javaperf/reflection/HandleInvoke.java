package com.tek271.javaperf.reflection;

import com.google.common.base.Verify;
import com.tek271.javaperf.model.Book;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

import static com.tek271.javaperf.model.Book.HITCHHIKER;

public class HandleInvoke {
	private static final VarHandle TITLE = createFieldHandle(Book.class, "title", String.class);
	private static final VarHandle PAGES = createFieldHandle(Book.class, "pages", int.class);
	private static final MethodHandle CALC_VALUE = createMethodHandle(Book.class, "calcValue", long.class);

	private static VarHandle createFieldHandle(Class<?> cls, String fieldName, Class<?> fieldType) {
		MethodHandles.Lookup lookup = MethodHandles.lookup().in(cls);
		try {
			return lookup.findVarHandle(cls, fieldName, fieldType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static MethodHandle createMethodHandle(Class<?> cls, String methodName, Class<?> methodReturnType) {
		MethodType methodType = MethodType.methodType(methodReturnType);
		try {
			return MethodHandles.publicLookup().findVirtual(cls, methodName, methodType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void readFields() {
		String title = (String) TITLE.get(HITCHHIKER);
		int pages = (Integer) PAGES.get(HITCHHIKER);

		Verify.verify(HITCHHIKER.title.equals(title), "Title is different");
		Verify.verify(HITCHHIKER.pages == pages, "pages is different");
	}

	public void callMethod() {
		long value;
		try {
			value = (long) CALC_VALUE.invoke(HITCHHIKER);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
		Verify.verify(value == 736_064, "Value is " + value);
	}

}
