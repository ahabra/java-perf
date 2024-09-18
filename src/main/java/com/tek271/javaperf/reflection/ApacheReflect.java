package com.tek271.javaperf.reflection;


import com.google.common.base.Verify;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import static com.tek271.javaperf.reflection.Book.HITCHHIKER;

public class ApacheReflect {

	public void readFields() {
		String title;
		int pages;
		try {
			title = (String) FieldUtils.readField(HITCHHIKER, "title");
			pages = (Integer) FieldUtils.readField(HITCHHIKER, "pages");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Verify.verify(HITCHHIKER.title.equals(title), "Title is different");
		Verify.verify(HITCHHIKER.pages == pages, "pages is different");
	}

	public void callMethod() {
		long value;
		try {
			value = (Long) MethodUtils.invokeExactMethod(HITCHHIKER, "calcValue");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Verify.verify(value == 736_064, "Value is " + value);
	}


}
