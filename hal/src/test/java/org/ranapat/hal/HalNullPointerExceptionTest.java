package org.ranapat.hal;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HalNullPointerExceptionTest {

    @Test
    public void shouldGenerateFields() {
        final HalNullPointerException halNullPointerException= new HalNullPointerException();

        assertThat(halNullPointerException.getMessage(), is(equalTo("Cannot handle null strings")));
        assertThat(halNullPointerException.toString(), is(equalTo("HalNullPointerException{}")));
    }

}