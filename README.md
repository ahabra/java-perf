# java-perf Created on Tue Sep 17 22:06:15 EDT 2024
Discover performance impact of different Java libraries.

## Reflection
Three approaches are tested:

1. Using `org.apache.commons.lang3.reflect`  FieldUtils and MethodUtils. This is the slowest approach.
2. Using classic Java reflection `Class` getField and getMethod. About twice as fast as using apache.
3. Using the `java.lang.invoke` VarHandle and MethodHandle (added in Java 1.7). About three times faster than classic.

