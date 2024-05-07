# hal

[![Coverage Status](https://coveralls.io/repos/github/ranapat/hal/badge.svg?branch=master)](https://coveralls.io/github/ranapat/hal?branch=master)

Android library for Hal url parameter parsing library

## Quick guide

### ```{name}``` - name is required
```/path/{name}``` will become ```/path/value```

### ```{?name}``` - name is optional
```/path/{?name}``` will become ```/path/?name=value```

or
```/path/{?name}``` will become ```/path/```

### ```{&name}``` - name is optional
```/path/{&name}``` will become ```/path/?name=value```

or
```/path/{&name}``` will become ```/path/```

### ```{#name}``` - name is optional
```/path/{#name}``` will become ```/path/?name=value```

or
```/path/{#name}``` will become ```/path/```

### ```{#name1,name2}``` - name1 and name2 are optional
```/path/{#name1,name2}``` will become ```/path/?name1=value1&name2=value2```

or
```/path/{#name1,name2}``` will become ```/path/?name1=value1```

or
```/path/{#name1,name2}``` will become ```/path/?name2=value2```

or
```/path/{#name1,name2}``` will become ```/path/```

### ```{@name}``` - name is nullable
```/path/{@name}``` will become ```/path/value```
or
```/path/{@name}``` will become ```/path/```

### ```{*name}``` - name is wild
```/path/{*name}``` will become ```/path/value```

or
```/path/{*name}``` will become ```/path/```

or
```/path/{*name}``` will become ```/path/complete/path/with/many```

### ```{!name}``` - name is wildest <img src="./assets/new.svg" alt="new" width="16" height="16" />
It's the same as `{*name}` wild, but with empty.


```/path/{!name}``` will become ```/path/value```

or
```/path/{!name}``` will become ```/path/```

or
```/path/{!name}``` will become ```/path/complete/path/with/many```

or
```/path/{!name}``` will become  ```/path/```

#### Important!

You cannot put more than one *wildest* variables in a row.
The first one will take all possible (hungry matching).
Cases like `{!name1}{!name2}` will result in all values going into `name1` and nothing in `name2`.
Also make sure that *wildest* will always match even if it's null.
Make sure you do not match false positive results because of this.

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

Check for match

```java
final String url = "something/{key1}";
final String string = "something/value1";

final List<HalParameterSet> parameters = HalMatcher.match(url, string);
final Map<String, String> map = HalMatcher.matchToMap(url, string);
```
