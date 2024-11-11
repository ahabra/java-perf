# java-perf Created on Tue Sep 17 22:06:15 EDT 2024
Discover performance impact of different Java libraries.

## Reflection
Three approaches are tested:

1. Using `org.apache.commons.lang3.reflect`  FieldUtils and MethodUtils. This is the slowest approach.
2. Using classic Java reflection `Class` getField and getMethod. About 4 to 5 times as fast as using apache.
3. Using the `java.lang.invoke` VarHandle and MethodHandle (added in Java 1.7). Similar to classic.

Observe that when using classic reflection or invoke package, the major performance gain is 
achieved because we can get a reference to the method or the field, cash it, then reuse 
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
2. `java.util.Random` : about 5 times slower than ThreadLocalRandom
3. `StrictMath.random()`: about 6 times slower than ThreadLocalRandom
4. `Math.random()`: about 10 times slower than ThreadLocalRandom
5. `SecureRandom.getInstanceStrong()`: about 100 times slower than ThreadLocalRandom
6. `new SecureRandom()`: about 110 times slower than ThreadLocalRandom


### Recommendation:
1. If you do not need a secure random, use ThreadLocalRandom.
2. If you do need a secure random, use SecureRandom.getInstanceStrong()
3. On some platforms, SecureRandom has the potential of blocking for long time. Be careful.


## Hashing

### Different SHA256 implementations

Using several approaches:
1. JDK standard MessageDigest.
2. Apache commons codec.
3. Guava.
4. SHA3_256 JDK.
5. SHA3_256 Apache commons Codec.
6. SHA3_256 Keccak. About 2 to 3 times slower than others.

The first 5 approaches perform relatively at the same scale, but SHA3 is slower than SHA2.

If you do not need a secure hash, then there are faster and simpler
algorithms. Consider using `org.apache.commons.codec.digest.XXHash32`.


## RegEx
When the string to find/replace is simple, consider using `org.apache.commons.lang3.StringUtils.replace()`
instead of `String.replaceAll()`, because replaceAll() takes a RegEx.

The `StringUtils.replace()` is about 50% faster than `String.replaceAll()`.

## HttpClient
Creating an instance of `HttpClient` is expensive, reuse the instance if you can.
In an example where an HTTP GET (to same URL) is called 1000 times, reusing the instance 
was 3 to 4 times faster and consumed about 1/3 of the memory.

Instances of `HttpClient` are not quickly garbage collected, so creating a lot of instances could
crash the JVM.

## Math.fma (Fused Multiply Add)
`Math.fma(a, b, c)` returns `a * b + c` but it does only one rounding instead of two, thus
`fma` is more accurate.


* Java 17: The `fma` method is about 15% faster than hand-coding the calculation.
* Java 21: The  `fma` method is as fast as the hand-coding the calculation;

