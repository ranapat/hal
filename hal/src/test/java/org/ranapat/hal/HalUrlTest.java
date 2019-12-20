package org.ranapat.hal;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class HalUrlTest {

    @Test
    public void shouldReplacePassedParametersCase1() {
        final HalUrl halUrl = new HalUrl("something/{key1}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/value1")));
    }

    @Test
    public void shouldReplacePassedParametersCase2() {
        final HalUrl halUrl = new HalUrl("something/{key1}/{key2}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");

        assertThat(halUrl.toString(), is(equalTo("something/value1/value2")));
    }

    @Test
    public void shouldReplacePassedParametersCase3() {
        final HalUrl halUrl = new HalUrl("something/{key1}/{key2}/{key3}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key3", 1);

        assertThat(halUrl.toString(), is(equalTo("something/value1/value2/1")));
    }

    @Test
    public void shouldReplacePassedParametersCase4() {
        final HalUrl halUrl = new HalUrl("something");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key3", 1);

        assertThat(halUrl.toString(), is(equalTo("something")));
    }

    @Test
    public void shouldReplacePassedParametersCase5() {
        final HalUrl halUrl = new HalUrl("something/{{key1}}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key3", 1);

        assertThat(halUrl.toString(), is(equalTo("something/{value1}")));
    }

    @Test
    public void shouldReplacePassedParametersCase6() {
        final HalUrl halUrl = new HalUrl("something/{{key1");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key3", 1);

        assertThat(halUrl.toString(), is(equalTo("something/{{key1")));
    }

    @Test
    public void shouldHandleOptionalParametersCase1() {
        final HalUrl halUrl = new HalUrl("something/{?key1}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1")));
    }

    @Test
    public void shouldHandleOptionalParametersCase2() {
        final HalUrl halUrl = new HalUrl("something/{&key1}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1")));
    }

    @Test
    public void shouldHandleOptionalParametersCase3() {
        final HalUrl halUrl = new HalUrl("something/{#key1}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1")));
    }

    @Test
    public void shouldHandleOptionalParametersCase4() {
        final HalUrl halUrl = new HalUrl("something/{@key1}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/{@key1}")));
    }

    @Test
    public void shouldHandleOptionalParametersCase5() {
        final HalUrl halUrl = new HalUrl("something/{#key1}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1")));
    }

    @Test
    public void shouldHandleOptionalParametersCase6() {
        final HalUrl halUrl = new HalUrl("something/{?key1,key2}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2")));
    }

    @Test
    public void shouldHandleOptionalParametersCase7() {
        final HalUrl halUrl = new HalUrl("something/{&key1,key2}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2")));
    }

    @Test
    public void shouldHandleOptionalParametersCase8() {
        final HalUrl halUrl = new HalUrl("something/{#key1,key2}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2")));
    }

    @Test
    public void shouldHandleOptionalParametersCase9() {
        final HalUrl halUrl = new HalUrl("something/{@key1,key2}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");

        assertThat(halUrl.toString(), is(equalTo("something/{@key1,key2}")));
    }

    @Test
    public void shouldHandleOptionalParametersCase10() {
        final HalUrl halUrl = new HalUrl("something/{?key1,key2}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1")));
    }

    @Test
    public void shouldHandleOptionalParametersCase11() {
        final HalUrl halUrl = new HalUrl("something/{?key1,key2}");

        assertThat(halUrl.toString(), is(equalTo("something/")));
    }

    @Test
    public void shouldHandleOptionalParametersCase12() {
        final HalUrl halUrl = new HalUrl("something/{?key1,key2}&key3=value3");

        assertThat(halUrl.toString(), is(equalTo("something/&key3=value3")));
    }

    @Test
    public void shouldHandleOptionalParametersCase13() {
        final HalUrl halUrl = new HalUrl("something/{?key1,key2}&key3=value3{?key4,key5}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key4", "value4");
        halUrl.addParameter("key5", "value5");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2&key3=value3&key4=value4&key5=value5")));
    }

    @Test
    public void shouldHandleOptionalParametersCase14() {
        final HalUrl halUrl = new HalUrl("something/{&key1,key2}&key3=value3{?key4,key5}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key4", "value4");
        halUrl.addParameter("key5", "value5");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2&key3=value3&key4=value4&key5=value5")));
    }

    @Test
    public void shouldHandleOptionalParametersCase15() {
        final HalUrl halUrl = new HalUrl("something/{&key1,key2}&key3=value3{&key4,key5}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key4", "value4");
        halUrl.addParameter("key5", "value5");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2&key3=value3&key4=value4&key5=value5")));
    }

    @Test
    public void shouldHandleOptionalParametersCase16() {
        final HalUrl halUrl = new HalUrl("something/{#key1,key2}&key3=value3{#key4,key5}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key4", "value4");
        halUrl.addParameter("key5", "value5");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2&key3=value3&key4=value4&key5=value5")));
    }

    @Test
    public void shouldHandleOptionalParametersCase17() {
        final HalUrl halUrl = new HalUrl("something/{#key1,key2}&key3=value3{#key4,key5}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key4", "value4");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2&key3=value3&key4=value4")));
    }

    @Test
    public void shouldHandleOptionalParametersCase18() {
        final HalUrl halUrl = new HalUrl("something/{#key1,key2}&key3=value3{#key4,key5}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key2=value2&key3=value3")));
    }

    @Test
    public void shouldHandleOptionalParametersCase19() {
        final HalUrl halUrl = new HalUrl("something/{#key1,key2}&key3=value3{#key4,key5}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/?key1=value1&key3=value3")));
    }

    @Test
    public void shouldHandleOptionalParametersCase20() {
        final HalUrl halUrl = new HalUrl("something/{#key1,key2}&key3=value3{#key4,key5}");
        halUrl.addParameter("key4", "value4");
        halUrl.addParameter("key5", "value5");

        assertThat(halUrl.toString(), is(equalTo("something/&key3=value3?key4=value4&key5=value5")));
    }

    @Test
    public void shouldHandleOptionalParametersCase21() {
        final HalUrl halUrl = new HalUrl("something/{#key1,key2}&key3=value3{#key4,key5}");
        halUrl.addParameter("key5", "value5");

        assertThat(halUrl.toString(), is(equalTo("something/&key3=value3?key5=value5")));
    }

    @Test
    public void shouldHandleMixedParametersCase1() {
        final HalUrl halUrl = new HalUrl("something/{key1}{#key2,key3}&key4=value4{#key5,key6}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key2", "value2");
        halUrl.addParameter("key3", "value3");
        halUrl.addParameter("key5", "value5");
        halUrl.addParameter("key6", "value6");

        assertThat(halUrl.toString(), is(equalTo("something/value1?key2=value2&key3=value3&key4=value4&key5=value5&key6=value6")));
    }

    @Test
    public void shouldHandleMixedParametersCase2() {
        final HalUrl halUrl = new HalUrl("something/{key1}{#key2,key3}&key4=value4{#key5,key6}");
        halUrl.addParameter("key1", "value1");

        assertThat(halUrl.toString(), is(equalTo("something/value1&key4=value4")));
    }

    @Test
    public void shouldHandleMixedParametersCase3() {
        final HalUrl halUrl = new HalUrl("something/{key1}{#key2,key3}&key4=value4{#key5,key6}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key6", "value6");

        assertThat(halUrl.toString(), is(equalTo("something/value1&key4=value4?key6=value6")));
    }

    @Test
    public void shouldHandleRemoveParametersCase1() {
        final HalUrl halUrl = new HalUrl("something/{key1}{#key2,key3}&key4=value4{#key5,key6}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key6", "value6");

        halUrl.addParameter("key5", "value5");
        halUrl.removeParameter("key5");

        assertThat(halUrl.toString(), is(equalTo("something/value1&key4=value4?key6=value6")));
    }

    @Test
    public void shouldHandleRemoveParametersCase2() {
        final HalUrl halUrl = new HalUrl("something/{key1}{#key2,key3}&key4=value4{#key5,key6}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("key6", "value6");

        halUrl.addParameter("key5", "value5");
        halUrl.removeParameter("key5");
        halUrl.removeParameter("key7");

        assertThat(halUrl.toString(), is(equalTo("something/value1&key4=value4?key6=value6")));
    }

    @Test
    public void shouldThrowOnMissingRequiredParametersCase1() {
        final HalUrl halUrl = new HalUrl("something/{key1}");

        try {
            halUrl.toString();
            fail("Method shall throw");
        } catch (final HalMissingRequiredParametersException e) {
            assertThat(e.getMessage(), is(equalTo("Missing required keys: key1")));
            assertThat(e.toString(), is(equalTo("HalMissingRequiredParametersException{missingKeys=key1}")));
        }
    }

    @Test
    public void shouldThrowOnMissingRequiredParametersCase2() {
        final HalUrl halUrl = new HalUrl("something/{key1}/{key2}");

        try {
            halUrl.toString();
            fail("Method shall throw");
        } catch (final HalMissingRequiredParametersException e) {
            assertThat(e.getMessage(), is(equalTo("Missing required keys: key1,key2")));
            assertThat(e.toString(), is(equalTo("HalMissingRequiredParametersException{missingKeys=key1,key2}")));
        }
    }

    @Test
    public void shouldThrowOnMissingRequiredParametersCase3() {
        final HalUrl halUrl = new HalUrl("something/{key1}/{key2}");
        halUrl.addParameter("key1", "value1");
        halUrl.addParameter("something", "else");

        try {
            halUrl.toString();
            fail("Method shall throw");
        } catch (final HalMissingRequiredParametersException e) {
            assertThat(e.getMessage(), is(equalTo("Missing required keys: key2")));
            assertThat(e.toString(), is(equalTo("HalMissingRequiredParametersException{missingKeys=key2}")));
        }
    }

    @Test
    public void shouldHandleNullsCase1() {
        final HalUrl halUrl = new HalUrl(null);

        try {
            halUrl.toString();
            fail("Method shall throw");
        } catch (final HalNullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Cannot handle null strings")));
            assertThat(e.toString(), is(equalTo("HalNullPointerException{}")));
        }
    }

}