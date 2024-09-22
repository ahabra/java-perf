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


## Primitive Collections
Using `eclipse-collections:MutableIntList` for a list on integers compared with JDK `List` class.

The `MutableIntList` is about 4 times faster than the JDK's `List`.

## Random Numbers
Using several approaches to generate random numbers, next is a list ordered by performance, fatser is first:

1. `java.util.concurrent.ThreadLocalRandom` : this was the fastest in generating random numbers
2. `java.util.Random` : about 5  times slower than ThreadLocalRandom
3. `StrictMath.random()`: roughly in the same range as java.util.Random
4. `Math.random()`: about 20% slower than StrictMath.random()
5. `SecureRandom.getInstanceStrong()`: About 8 times slower than Math.random()
6. `new SecureRandom()`: About 30% slower than SecureRandom.getInstanceStrong()

In general, the fastest approach is about 75 times slower than the slowest.

### Recommendation:
1. If you do not need a secure random, use ThreadLocalRandom.
2. If you do need a secure random, use SecureRandom.getInstanceStrong()


## Hashing


## NIO servlets


## RegEx

