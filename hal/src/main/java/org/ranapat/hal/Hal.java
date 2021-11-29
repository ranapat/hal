package org.ranapat.hal;

import java.util.Map;

public final class Hal {
    private Hal() {}

    public static Map<String, String> match(final String pattern, final String url) throws HalException {
        return null;
    }

    public static String parse(final String url) throws HalException {
        return new HalUrl(url).toString();
    }

    public static String parse(final String url, final Map<String, Object> parameters) throws HalException {
        final HalUrl halUrl = new HalUrl(url);
        for (final String key : parameters.keySet()) {
            halUrl.addParameter(key, parameters.get(key));
        }
        return halUrl.toString();
    }

    public static String safe(final String url) {
        try {
            return parse(url);
        } catch (final HalException e) {
            return url;
        }
    }

    public static String safe(final String url, final Map<String, Object> parameters) {
        try {
            return parse(url, parameters);
        } catch (final HalException e) {
            return url;
        }
    }

    public static HalUrl get(final String url) {
        return new HalUrl(url);
    }
}
