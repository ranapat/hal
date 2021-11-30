package org.ranapat.hal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class HalParameterTest {

    @Test
    public void shouldPopulateFields() {
        final HalParameter parameter = new HalParameter("name", HalParameter.Type.Required);

        assertThat(parameter.name, is(equalTo("name")));
        assertThat(parameter.type, is(equalTo(HalParameter.Type.Required)));
    }

}