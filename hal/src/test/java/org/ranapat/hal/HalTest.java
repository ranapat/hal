package org.ranapat.hal;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HalTest {

    @Test
    public void shouldGetHalUrl() {
        final HalUrl halUrl = Hal.get("something");

        assertThat(halUrl, is(CoreMatchers.<HalUrl>instanceOf(HalUrl.class)));
    }

    @Test
    public void shouldParseHalUrlCase1() {
        final String parsed = Hal.parse("something");

        assertThat(parsed, is(equalTo("something")));
    }

    @Test
    public void shouldParseHalUrlCase2() {
        final String parsed = Hal.parse("something/{key1}", new HashMap<String, Object>() {{
            put("key1", "value1");
        }});

        assertThat(parsed, is(equalTo("something/value1")));
    }

    @Test
    public void shouldParseHalUrlCase3() {
        final String parsed = Hal.parse("something/{key1}/{?key2}", new HashMap<String, Object>() {{
            put("key1", "value1");
            put("key2", "value2");
        }});

        assertThat(parsed, is(equalTo("something/value1/?key2=value2")));
    }

    @Test
    public void shouldParseHalUrlCase4() {
        final String parsed = Hal.parse("something/{key1}/{?key2}", new HashMap<String, Object>() {{
            put("key1", "value1");
        }});

        assertThat(parsed, is(equalTo("something/value1/")));
    }

    @Test
    public void shouldThrowCase1() {
        try {
            final String parsed = Hal.parse("something/{key1}");
            fail("Method shall throw");
        } catch (final HalMissingRequiredParametersException e) {
            assertThat(e.getMessage(), is(equalTo("Missing required keys: key1")));
            assertThat(e.toString(), is(equalTo("HalMissingRequiredParametersException{missingKeys=key1}")));
        }
    }

    @Test
    public void shouldThrowCase2() {
        try {
            final String parsed = Hal.parse("something/{key1}", new HashMap<String, Object>());
            fail("Method shall throw");
        } catch (final HalMissingRequiredParametersException e) {
            assertThat(e.getMessage(), is(equalTo("Missing required keys: key1")));
            assertThat(e.toString(), is(equalTo("HalMissingRequiredParametersException{missingKeys=key1}")));
        }
    }

    @Test
    public void shouldSafelyParseCase1() {
        final String parsed = Hal.safe("something");

        assertThat(parsed, is(equalTo("something")));
    }

    @Test
    public void shouldSafelyParseCase2() {
        final String parsed = Hal.safe("something/{key1}");

        assertThat(parsed, is(equalTo("something/{key1}")));
    }

    @Test
    public void shouldSafelyParseCase3() {
        final String parsed = Hal.safe("something/{key1}", new HashMap<String, Object>() {{
            put("key1", "value1");
        }});

        assertThat(parsed, is(equalTo("something/value1")));
    }

    @Test
    public void shouldSafelyParseCase4() {
        final String parsed = Hal.safe("something/{key1}", new HashMap<String, Object>() {{
            put("key11", "value1");
        }});

        assertThat(parsed, is(equalTo("something/{key1}")));
    }

    @Test
    public void shouldSafelyParseCase5() {
        final String parsed = Hal.safe("something/{?key1}", new HashMap<String, Object>() {{
            put("key1", "value1");
        }});

        assertThat(parsed, is(equalTo("something/?key1=value1")));
    }

    @Test
    public void shouldSafelyParseCase6() {
        final String parsed = Hal.safe("something/{?key1}", new HashMap<String, Object>() {{
        }});

        assertThat(parsed, is(equalTo("something/")));
    }

}