# High Performance Computing with Java
### Windsor-Essex GDG DevFest 2024

* Presented by: **Abdul Habra**, @ahabra
* 2024.11.16
* https://github.com/ahabra/java-perf


## Table of Contents
1. Introduction
2. The Usual Suspects
3. Collections
4. Fused Multiply Add (FMA)
5. JSON Parsing
6. HttpClient
7. RegEx
8. Random Numbers
9. Hashing
10. Reflection
11. References

<div style="page-break-after: always"></div>

## 1. Introduction

* You should be experienced with Java
* Build the system to be valid and well-designed
* _Premature optimization is the root of all evil_ -- Donald Knuth
* Understand the bottlenecks of your program before attempting to optimize
* Will point at different approaches to increase the performance of Java programs.
* Will NOT discuss JVM fine-tuning, or scaling.


In the following sections, we will use a `Book` class defined as follows:

```java
public class Book {
  public String title;
  public int pages;
  public String author;

  public Book(String title, int pages, String author) { /* ... */ }
}
```

<div style="page-break-after: always"></div>

## 2. The Usual Suspects

The following approaches are the usual recommendations, and are well documented in the literature.

* StringBuilder: For String concatenation in conditionals and loops
* Caching/Memoization/Pooling
* In JDBC Use Prepared Statement instead of Statement
* When logging, check the log level before you call the log statement

<div style="page-break-after: always"></div>

## 3. Collections
When dealing with a List (or other collection types) of a primitive type,
Java generics will use the wrapper type, for example:

```java
List<Integer> ok = List.of(1, 2, 3);
List<int> notOk = List.of(1, 2, 3);  // compiler error
```

You can use the `eclipse-collections-api` library which provides primitives
collections types like `IntArrayList`.

```java
void usingPrimitivesList() {
  IntArrayList list = new IntArrayList();
  list.add(1);
}
```

`IntArrayList` is about __4 times faster__ than the JDK `List<Integer>`,
and it consumes less memory.

* Supported primitives: `boolean`, `char`, `byte`, `short`, `int`, `long`, `float`, `double`
* Supported collections: list, map, set, stack. 

<div style="page-break-after: always"></div>

## 4. Fused Multiply Add (FMA)
In applications which heavily use floating point calculations like in machine learning, scientific
measurements, or financial applications, a common calculation happens:

1. Multiply two numbers
2. Add the product to an accumulator

Or simply calculate `a * b + c` where a, b, and c are `float` or `double`.

Two roundings can occur: 
* when multiplying a and b. 
* when adding the product to c.

To enhance the calculation, you can use `Math.fma(a, b, c)` which will do only one rounding.

The `fma()` method is about 15% faster (in Java 17) than the hand-coded calculation.

<div style="page-break-after: always"></div>

## 5. JSON Parsing
There are many JSON parsing libraries, one of the fast/popular ones is `com.fasterxml.jackson.core:jackson-databind`.
It is tested here.

Two approaches are tested:

1. Using `ObjectMapper`. Simple to implement.
2. Using `JsonParser`. Harder to implement. About 10 times faster than using
	 `ObjectMapper`.

Suppose we have the following JSON string describing a book:

```json
{
  "title": "Cat in the Hat",
  "pages": 20,
  "author": "Dr. Seuss"
}
```


### JSON Parsing With ObjectMapper Example
```java
ObjectMapper objectMapper = new ObjectMapper();
Book book = objectMapper.readValue(jsonText, Book.class);
```

### JSON Parsing With JsonParser Example
```java
// Example only. Do NOT use in production
Book parseToBook(String jsonText) throws Exception {
  JsonParser parser = new JsonFactory().createParser(jsonText);
  Book book = new Book();
  
  parser.nextToken();
  while (true) {
    JsonToken token = parser.nextToken();
    if (token == null || token == JsonToken.END_OBJECT) break;
    
    String fieldName = parser.currentName();
    if (fieldName == null) {
      skipCurrentNode(parser);
      break;
    }
    if (fieldName.equals("title")) {
      book.title = nextValueAsString(parser);
    } else if (fieldName.equals("pages")) {
      book.pages = nextValueAsInt(parser);
    } else if (fieldName.equals("author")) {
      book.author = nextValueAsString(parser);
    } else {
      skipCurrentNode(parser);
    }
  }
  parser.close();
  return book;
}

void skipCurrentNode(JsonParser parser) throws IOException {
  JsonToken token = parser.nextToken();
  if (token.isScalarValue() || token.isStructEnd()) {
    return;
  }
  if (token.isStructStart()) {
    skipCurrentNode(parser);
  }
}

String nextValueAsString(JsonParser parser) throws IOException {
  parser.nextToken();
  return parser.getValueAsString();
}

int nextValueAsInt(JsonParser parser) throws IOException {
  parser.nextToken();
  return parser.getIntValue();
}
```

<div style="page-break-after: always"></div>

## 6. HttpClient
Creating an instance of `HttpClient` is expensive, reuse the instance if you can.
In an example where an HTTP GET (to same URL) is called 1000 times, reusing the instance
was 3 to 4 times faster and consumed about 1/3 of the memory.

Instances of `HttpClient` are not quickly garbage collected, so creating a lot of instances could
crash the JVM.

<div style="page-break-after: always"></div>

## 7. RegEx
When the string to find/replace is simple, consider using `org.apache.commons.lang3.StringUtils.replace()`
instead of `String.replaceAll()`, because replaceAll() takes a RegEx.

The `StringUtils.replace()` is about 50% faster than `String.replaceAll()`.

<div style="page-break-after: always"></div>

## 8. Random Numbers
Several approaches to generate random numbers, next is a list ordered by performance, faster is first:

1. `java.util.concurrent.ThreadLocalRandom` : the fastest in generating random numbers
2. `java.util.Random` : about 5  times slower than `ThreadLocalRandom`
3. `StrictMath.random()`: about 6 times slower than ThreadLocalRandom
4. `Math.random()`: about 10 times slower than `ThreadLocalRandom`
5. `SecureRandom.getInstanceStrong()`: about 100 times slower than `ThreadLocalRandom`
6. `new SecureRandom()`: about 110 times slower than `ThreadLocalRandom`


### Recommendation:
1. If you do not need a secure random, use `ThreadLocalRandom`.
2. If you do need a secure random, use `SecureRandom.getInstanceStrong()`
3. On some OS platforms, SecureRandom has the potential of blocking for long time. Be careful.

<div style="page-break-after: always"></div>

## 9. Hashing

### Definition
1. __Hashing__: Convert data to a random-looking string
2. Same data will __always__ generate the same hash string 
3. Different data, ideally, __should__ generate different hash strings
4. Different data can _in theory_ produce the same hash string, this is called _collision_
5. Hashing functions attempt to minimize collisions 
6. Secure hashing functions attempt to hinder converting the hash string to its original value 
7. Hash algorithms examples: MD5, CRC, Murmum, SHA1 (20 bytes), SHA256 (32 bytes), SHA512 (64 bytes)
8. For secure hashing, use at least (Secure Hash Algorithm 256 bits) SHA256

### Algorithms
To produce secure _SHA256_ hashes, there are several options:

1. JDK standard MessageDigest.
2. Apache commons codec.
3. Guava.
4. SHA3_256 JDK.
5. SHA3_256 Apache commons Codec.
6. SHA3_256 Keccak (Sounds like Ketchak) (Bouncy Castle Library). About 2 to 3 times slower than others.

* The first 5 approaches perform relatively at the same scale
* SHA3 is newer, slower, and supposed to be more secure than SHA2


If you do not need a secure hash, then there are faster and simpler
algorithms. Consider using `org.apache.commons.codec.digest.XXHash32`.

<div style="page-break-after: always"></div>

## 10. Reflection
Three approaches are tested:

1. Using `org.apache.commons.lang3.reflect`  FieldUtils and MethodUtils. This is the slowest approach.
2. Using classic Java reflection `Class` getField and getMethod. About 4 to 5 times as fast as using apache.
3. Using the `java.lang.invoke` VarHandle and MethodHandle (added in Java 1.7). Similar to classic.

Observe that when using classic reflection or invoke package, the major performance gain is
achieved because we can get a reference to the method or the field, cash it , then reuse
it during the repeated invocations.

### Examples
Use reflection to read one of the `Book` fields:

```java
Book book = new Book("Cat in the Hat", 20, "Dr. Seuss");

// slow
String getTitle_apache() {
  return (String) FieldUtils.readField(book, "title");
}

// fast
String getTitle_classicReflect() throws Exception {
  Field titleField = Book.class.getField("title");
  return (String) titleField.get(book);
}

// supposed to be faster
String getTitle_handle() throws Exception {
  MethodHandles.Lookup lookup = MethodHandles.lookup().in(Book.class);
  VarHandle varHandle = lookup.findVarHandle(Book.class, "title", String.class);
  return (String) varHandle.get(book);
}
```


<div style="page-break-after: always"></div>

## References
1. https://www.bairesdev.com/blog/java-performance-tuning-tips/
2. https://raygun.com/blog/java-performance-optimization-tips/
3. https://stackify.com/java-performance-tuning/
4. https://stratoflow.com/improve-java-performance/