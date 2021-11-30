package org.ranapat.hal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class HalNullPointerExceptionTest {

    @Test
    public void shouldGenerateFields() {
        final HalNullPointerException halNullPointerException= new HalNullPointerException();

        assertThat(halNullPointerException.getMessage(), is(equalTo("Cannot handle null strings")));
        assertThat(halNullPointerException.toString(), is(equalTo("HalNullPointerException{}")));
    }

}