# java-perf Created on Tue Sep 17 22:06:15 EDT 2024
Discover performance impact of different Java libraries.

## Reflection
Three approaches are tested:

1. Using `org.apache.commons.lang3.reflect`  FieldUtils and MethodUtils. This is the slowest approach.
2. Using classic Java reflection `Class` getField and getMethod. About 4 to 5 times as fast as using apache.
3. Using the `java.lang.invoke` VarHandle and MethodHandle (added in Java 1.7). Similar to classic.

Observe that when using classic reflection or invoke package, the major performance gain is 
achieved because we can get a reference to the method or the field, cash it , then reuse 
it during the repeated tests.

## JSON Parsing
Using `com.fasterxml.jackson.core:jackson-databind` library to deserialize a JSON text to
a simple object graph.

Two approaches are tested:

1. Using `ObjectMapper`. Simple to implement. About 27 lines of code.
2. Using `JsonParser`. Harder to implement. About 150 lines of code. About 10 times faster than using
	 `ObjectMapper`.
