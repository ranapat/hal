package org.ranapat.hal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static java.util.Arrays.asList;

import org.junit.Test;

public class HalMissingRequiredParametersExceptionTest {

    @Test
    public void shouldGetMissingKeysCase1() {
        final HalMissingRequiredParametersException halMissingRequiredParametersException = new HalMissingRequiredParametersException(asList("key1"));

        assertThat(halMissingRequiredParametersException.getMissingKeys(), is(equalTo(asList("key1"))));
    }

    @Test
    public void shouldGetMissingKeysCase2() {
        final HalMissingRequiredParametersException halMissingRequiredParametersException = new HalMissingRequiredParametersException(asList("key1", "key2"));

        assertThat(halMissingRequiredParametersException.getMissingKeys(), is(equalTo(asList("key1", "key2"))));
    }

    @Test
    public void shouldGenerateProperStringCase1() {
        final HalMissingRequiredParametersException halMissingRequiredParametersException = new HalMissingRequiredParametersException(asList("key1"));

        assertThat(halMissingRequiredParametersException.toString(), is(equalTo("HalMissingRequiredParametersException{missingKeys=key1}")));
    }

    @Test
    public void shouldGenerateProperStringCase2() {
        final HalMissingRequiredParametersException halMissingRequiredParametersException = new HalMissingRequiredParametersException(asList("key1", "key2"));

        assertThat(halMissingRequiredParametersException.toString(), is(equalTo("HalMissingRequiredParametersException{missingKeys=key1,key2}")));
    }

    @Test
    public void shouldGenerateProperMessageCase1() {
        final HalMissingRequiredParametersException halMissingRequiredParametersException = new HalMissingRequiredParametersException(asList("key1"));

        assertThat(halMissingRequiredParametersException.getMessage(), is(equalTo("Missing required keys: key1")));
    }

    @Test
    public void shouldGenerateProperMessageCase2() {
        final HalMissingRequiredParametersException halMissingRequiredParametersException = new HalMissingRequiredParametersException(asList("key1", "key2"));

        assertThat(halMissingRequiredParametersException.getMessage(), is(equalTo("Missing required keys: key1,key2")));
    }

}