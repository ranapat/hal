package org.ranapat.hal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class HalParameterSetTest {

    @Test
    public void shouldPopulateFields() {
        final HalParameterSet parameterSet = new HalParameterSet("name", HalParameter.Type.Required, "value");

        assertThat(parameterSet.name, is(equalTo("name")));
        assertThat(parameterSet.type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameterSet.value, is(equalTo("value")));
    }

}