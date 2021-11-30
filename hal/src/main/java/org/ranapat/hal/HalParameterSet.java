package org.ranapat.hal;

public class HalParameterSet extends HalParameter {
    public final String value;

    public HalParameterSet(final String name, final Type type, final String value) {
        super(name, type);

        this.value = value;
    }
}
