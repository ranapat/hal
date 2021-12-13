package org.ranapat.hal;

import java.util.regex.Pattern;

public final class HalConstants {
    public final static Pattern requiredPattern = Pattern.compile("\\{(\\w+)\\}");
    public final static Pattern optionalPattern = Pattern.compile("\\{[\\?&#]([\\w,]+)\\}");
    public final static Pattern nullablePattern = Pattern.compile("\\{[@]([\\w]+)\\}");
    public final static Pattern wildPattern = Pattern.compile("\\{[\\*]([^\\}]+)\\}");

    private HalConstants() {}
}
