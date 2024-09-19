package com.tek271.javaperf.reflection;


import com.tek271.javaperf.utils.Prove;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import static com.tek271.javaperf.model.Book.HITCHHIKER;

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
		Prove.equal(HITCHHIKER.title, title, "title");
		Prove.equal(HITCHHIKER.pages, pages, "pages");
	}

	public void callMethod() {
		long value;
		try {
			value = (Long) MethodUtils.invokeExactMethod(HITCHHIKER, "calcValue");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Prove.equal(736_064L, value, "value");
	}


}
