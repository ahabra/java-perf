package com.tek271.javaperf.text;

import com.tek271.javaperf.utils.FileTools;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Replacer {
	private static final String TEXT = FileTools.readResource("usa-const.txt");
	private static final Pattern PATTERN = Pattern.compile("Section");

	public String withStringReplaceAll() {
		return TEXT.replaceAll("Section", "-SECTION-");
	}

	public String withApacheCommons() {
		return StringUtils.replace(TEXT, "Section", "-SECTION-");
	}

	public String withStringReplaceAllRegEx() {
		return PATTERN.matcher(TEXT).replaceAll("-SECTION-");
	}


}
