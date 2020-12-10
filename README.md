# hal

[![Coverage Status](https://coveralls.io/repos/github/ranapat/hal/badge.svg?branch=master)](https://coveralls.io/github/ranapat/hal?branch=master)

Android library for Hal url parameter parsing library

## How to get it

### Get it from jitpack
[![](https://jitpack.io/v/ranapat/hal.svg)](https://jitpack.io/#ranapat/hal)
[![](https://jitci.com/gh/ranapat/hal/svg)](https://jitci.com/gh/ranapat/hal)

## Requirements
* Java 8
* Android SDK
* Gradle

## Building
Build tool is gradle

### Assemble
Run `./gradlew assemble`

### Run unit tests
Run `./gradlew test`

### Run lint
Run `./gradlew lint`

### Run jacoco tests
Run `./gradlew testReleaseUnitTestCoverage`

### Outputs
You can find the outputs here:
- for the lint
`./hal/build/reports/lint-results-developmentDebug.html`
- for the unit test coverage
`./hal/build/reports/jacoco/testReleaseUnitTestCoverage/html/index.html`
- for the unit test summary
`./hal/build/reports/tests/testReleaseUnitTestCoverage/index.html`

### Examples

Parse parameterized url strings and handle exceptions

```java
Hal.parse("https://domain.com/{key}", new HashMap<String, Object>() {{
    put("key", "value");
}});
Hal.parse("https://domain.com/{?key1,key2}", new HashMap<String, Object>() {{
    put("key1", "value1");
    put("key2", "value2");
}});
Hal.parse("https://domain.com/{@key}", new HashMap<String, Object>() {{
    put("key", "value");
}});
Hal.parse("https://domain.com/{@key}", new HashMap<String, Object>() {{
    put("key1", "value1");
}});
try {
    Hal.parse("https://domain.com/{key}", new HashMap<String, Object>() {{
        put("_key", "value");
    }});
} catch (final HalException e) {
    e.printStackTrace();
}
```

Or get exception safe results

```java
Hal.safe("https://domain.com/{key}", new HashMap<String, Object>() {{
    put("_key", "value");
}});
``` 
