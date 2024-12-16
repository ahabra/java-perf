package com.tek271.javaperf.text;


import com.tek271.javaperf.model.Book;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

public class Stringer {
  private static final Book BOOK = Book.HITCHHIKER;

  public String toString_concat() {
    return "Title=" + BOOK.title + ", Pages=" + BOOK.pages + ", Author=" + BOOK.author.toString();
  }

  public String toString_StringBuilder()  {
    StringBuilder sb = new StringBuilder();
    sb.append("Title=").append(BOOK.title);
    sb.append(", Pages=").append(BOOK.pages);
    sb.append(", Author=").append(BOOK.author.toString());
    return sb.toString();
  }

  public String toString_reflection() {
    return ToStringBuilder.reflectionToString(BOOK, SIMPLE_STYLE);
  }

  public String toString_builder() {
    return new ToStringBuilder(BOOK, SIMPLE_STYLE).
        append("Title", BOOK.title)
        .append("Pages", BOOK.pages)
        .append("Author", BOOK.author.toString())
        .toString();
  }


}
