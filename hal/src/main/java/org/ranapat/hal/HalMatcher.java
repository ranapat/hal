package org.ranapat.hal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HalMatcher {
    private HalMatcher() {}

    public static List<HalParameterSet> safe(final String url, final String string) {
        try {
            return match(url, string);
        } catch (final HalException e) {
            return null;
        }
    }

    public static Map<String, String> safeMap(final String url, final String string) {
        try {
            return map(url, string);
        } catch (final Exception e) {
            return null;
        }
    }

    public static List<HalParameterSet> match(final String url, final String string) throws HalException {
        if (string == null) {
            throw new HalNullPointerException();
        }
        if (url != null && url.equals(string)) {
            return new ArrayList<>();
        }

        final HalUrl halUrl = new HalUrl(url);
        final List<HalParameter> parameters = halUrl.getParameters();
        final List<HalParameter> usedParameters = new ArrayList<>();

        for (final HalParameter parameter : parameters) {
            if (parameter.type == HalParameter.Type.Required) {
                halUrl.addParameter(parameter.name, "(\\\\w+)");
                usedParameters.add(parameter);
            } else if (parameter.type == HalParameter.Type.Optional) {
                if (string.indexOf(parameter.name) != -1) {
                    halUrl.addParameter(parameter.name, "(\\w+)");
                    usedParameters.add(parameter);
                }
            } else if (parameter.type == HalParameter.Type.Wild) {
                halUrl.addParameter(parameter.name, "(.+)");
                usedParameters.add(parameter);
            } else {
                halUrl.addParameter(parameter.name, "(\\\\w*)");
                usedParameters.add(parameter);
            }
        }

        final String regexString = "^"
                + halUrl.toString()
                .replaceAll("\\?", "\\\\?")
                + "$";
        final Pattern pattern = Pattern.compile(regexString);
        final Matcher matcher = pattern.matcher(string);

        final List<HalParameterSet> located = new ArrayList<>();
        boolean found = false;

        while (matcher.find()) {
            if (matcher.groupCount() == usedParameters.size()) {
                found = true;

                for (int i = 0; i < usedParameters.size(); ++i) {
                    final String value = matcher.group(i + 1);
                    located.add(new HalParameterSet(
                            usedParameters.get(i).name, usedParameters.get(i).type,
                            value.isEmpty() ? null : value
                    ));
                }
            }
        }

        if (found) {
            final List<HalParameterSet> result = new ArrayList<>();

            for (final HalParameter parameter : parameters) {
                boolean set = false;
                for (final HalParameterSet parameterSet : located) {
                    if (parameter.name.equals(parameterSet.name)) {
                        result.add(parameterSet);

                        set = true;
                    }
                }
                if (!set) {
                    result.add(new HalParameterSet(
                            parameter.name, parameter.type,
                            null
                    ));
                }
            }

            return result;
        } else {
            return null;
        }
    }

    public static Map<String, String> map(final String url, final String string) throws HalException {
        final List<HalParameterSet> match = match(url, string);

        if (match != null) {
            final Map<String, String> result = new HashMap<>();
            for (final HalParameterSet parameter : match) {
                result.put(parameter.name, parameter.value);
            }
            return result;
        } else {
            return null;
        }
    }
}
