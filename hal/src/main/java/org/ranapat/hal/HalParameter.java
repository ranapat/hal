package org.ranapat.hal;

public class HalParameter {
    enum Type {
        Required,
        Optional,
        Nullable
    }

    public final String name;
    public final Type type;

    public HalParameter(final String name, final Type type) {
        this.name = name;
        this.type = type;
    }
}
