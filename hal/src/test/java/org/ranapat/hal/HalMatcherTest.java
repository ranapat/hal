package org.ranapat.hal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class HalMatcherTest {

    @Test
    public void shouldMatchCase1() {
        final String url = "something/{key1}";
        final String string = "something/value1";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(1)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));
    }

    @Test
    public void shouldMatchCase2() {
        final String url = "something/{key1}/{key2}";
        final String string = "something/value1/value2";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(2)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(1).value, is(equalTo("value2")));
    }

    @Test
    public void shouldMatchCase3() {
        final String url = "something/{key1}/{?key2}";
        final String string = "something/value1/?key2=value2";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(2)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo("value2")));
    }

    @Test
    public void shouldMatchCase4() {
        final String url = "something/{key1}/{?key2,key3}";
        final String string = "something/value1/?key2=value2&key3=value3";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(3)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo("value2")));

        assertThat(parameters.get(2).name, is(equalTo("key3")));
        assertThat(parameters.get(2).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(2).value, is(equalTo("value3")));
    }

    @Test
    public void shouldMatchCase5() {
        final String url = "something/{key1}/{?key2}";
        final String string = "something/value1/";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(2)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase6() {
        final String url = "something/{?key1}";
        final String string = "something/";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(1)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(0).value, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase7() {
        final String url = "something/{?key1}";
        final String string = "something/";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(1)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(0).value, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase8() {
        final String url = "something/{@key1}";
        final String string = "something/";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(1)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(0).value, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase9() {
        final String url = "something/{@key1}";
        final String string = "something/value1";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(1)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));
    }

    @Test
    public void shouldMatchCase10() {
        final String url = "something/{@key1}";
        final String string = "something/";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(1)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(0).value, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase11() {
        final String url = "something/{key1}/{?key2,key3}/{@key4}";
        final String string = "something/value1/?key2=value2&key3=value3/value4";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(4)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo("value2")));

        assertThat(parameters.get(2).name, is(equalTo("key3")));
        assertThat(parameters.get(2).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(2).value, is(equalTo("value3")));

        assertThat(parameters.get(3).name, is(equalTo("key4")));
        assertThat(parameters.get(3).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(3).value, is(equalTo("value4")));
    }

    @Test
    public void shouldMatchCase12() {
        final String url = "something/{key1}/{?key2,key3}/{@key4}";
        final String string = "something/value1/?key2=value2/value4";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(4)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo("value2")));

        assertThat(parameters.get(2).name, is(equalTo("key3")));
        assertThat(parameters.get(2).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(2).value, is(equalTo(null)));

        assertThat(parameters.get(3).name, is(equalTo("key4")));
        assertThat(parameters.get(3).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(3).value, is(equalTo("value4")));
    }

    @Test
    public void shouldMatchCase13() {
        final String url = "something/{key1}/{?key2,key3}/{@key4}";
        final String string = "something/value1/?key2=value2/";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(4)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo("value2")));

        assertThat(parameters.get(2).name, is(equalTo("key3")));
        assertThat(parameters.get(2).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(2).value, is(equalTo(null)));

        assertThat(parameters.get(3).name, is(equalTo("key4")));
        assertThat(parameters.get(3).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(3).value, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase14() {
        final String url = "something/{key1}/{?key2,key3}/{@key4}";
        final String string = "something/value1//value4";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(4)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo(null)));

        assertThat(parameters.get(2).name, is(equalTo("key3")));
        assertThat(parameters.get(2).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(2).value, is(equalTo(null)));

        assertThat(parameters.get(3).name, is(equalTo("key4")));
        assertThat(parameters.get(3).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(3).value, is(equalTo("value4")));
    }

    @Test
    public void shouldMatchCase15() {
        final String url = "something/{key1}/{?key2,key3}/{@key4}";
        final String string = "something/value1//";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters.size(), is(equalTo(4)));

        assertThat(parameters.get(0).name, is(equalTo("key1")));
        assertThat(parameters.get(0).type, is(equalTo(HalParameter.Type.Required)));
        assertThat(parameters.get(0).value, is(equalTo("value1")));

        assertThat(parameters.get(1).name, is(equalTo("key2")));
        assertThat(parameters.get(1).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(1).value, is(equalTo(null)));

        assertThat(parameters.get(2).name, is(equalTo("key3")));
        assertThat(parameters.get(2).type, is(equalTo(HalParameter.Type.Optional)));
        assertThat(parameters.get(2).value, is(equalTo(null)));

        assertThat(parameters.get(3).name, is(equalTo("key4")));
        assertThat(parameters.get(3).type, is(equalTo(HalParameter.Type.Nullable)));
        assertThat(parameters.get(3).value, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase95() {
        final String url = "something(.*)/{key1}";
        final String string = "something/value1";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase96() {
        final String url = "something/{key1}";
        final String string = "something-else/something/value1";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase97() {
        final String url = "something/{key1}";
        final String string = "something/value1/";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase98() {
        final String url = "something/{key1}";
        final String string = "something/value1/something-else";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters, is(equalTo(null)));
    }

    @Test
    public void shouldMatchCase99() {
        final String url = "something/{key1}";
        final String string = "something-value1";

        final List<HalParameterSet> parameters = HalMatcher.match(url, string);

        assertThat(parameters, is(equalTo(null)));
    }

    @Test
    public void shouldMatchToMapCase1() {
        final String url = "something/{key1}/{?key2,key3}/{@key4}";
        final String string = "something/value1/?key2=value2&key3=value3/value4";

        final Map<String, String> map = HalMatcher.matchToMap(url, string);

        assertThat(map.size(), is(equalTo(4)));

        assertThat(map.get("key1"), is(equalTo("value1")));
        assertThat(map.get("key2"), is(equalTo("value2")));
        assertThat(map.get("key3"), is(equalTo("value3")));
        assertThat(map.get("key4"), is(equalTo("value4")));
    }

    @Test
    public void shouldMatchToMapCase2() {
        final String url = "something/{key1}/{?key2,key3}/{@key4}";
        final String string = "something/";

        final Map<String, String> map = HalMatcher.matchToMap(url, string);

        assertThat(map, is(equalTo(null)));
    }

}