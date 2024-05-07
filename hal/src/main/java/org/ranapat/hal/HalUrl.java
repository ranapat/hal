package org.ranapat.hal;

import static org.ranapat.hal.HalConstants.nullablePattern;
import static org.ranapat.hal.HalConstants.optionalPattern;
import static org.ranapat.hal.HalConstants.requiredPattern;
import static org.ranapat.hal.HalConstants.wildPattern;
import static org.ranapat.hal.HalConstants.wildestPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public final class HalUrl {
    private final String url;
    private final Map<String, String> parameters;

    public HalUrl(final String url) {
        this.url = url;
        this.parameters = new HashMap<>();
    }

    public void addParameter(final String key, final Object value) {
        parameters.put(key, value.toString());
    }

    public void removeParameter(final String key) {
        if (parameters.containsKey(key)) {
            parameters.remove(key);
        }
    }

    public List<HalParameter> getParameters() throws HalException {
        if (url == null) {
            throw new HalNullPointerException();
        }

        final List<HalParameter> result = new ArrayList<>();

        final List<String> required = getRequiredKeys();
        final List<String> optional = getOptionalKeys();
        final List<String> nullable = getNullableKeys();
        final List<String> wild = getWildKeys();
        final List<String> wildest = getWildestKeys();

        for (final String key : required) {
            result.add(new HalParameter(key, HalParameter.Type.Required));
        }
        for (final String key : optional) {
            result.add(new HalParameter(key, HalParameter.Type.Optional));
        }
        for (final String key : nullable) {
            result.add(new HalParameter(key, HalParameter.Type.Nullable));
        }
        for (final String key : wild) {
            result.add(new HalParameter(key, HalParameter.Type.Wild));
        }
        for (final String key : wildest) {
            result.add(new HalParameter(key, HalParameter.Type.Wildest));
        }

        return result;
    }

    @Override
    public String toString() throws HalException {
        return parse();
    }

    private String parse() throws HalException {
        if (url == null) {
            throw new HalNullPointerException();
        }

        String result = url;

        final List<String> provided = new ArrayList<>(parameters.keySet());
        final List<String> required = getRequiredKeys();
        final List<String> optional = getOptionalKeys();
        final List<String> nullable = getNullableKeys();
        final List<String> wild = getWildKeys();
        final List<String> wildest = getWildestKeys();

        if (!provided.containsAll(required)) {
            throw new HalMissingRequiredParametersException(getMissing(required, provided));
        }

        result = populateRequired(result, required);
        result = populateOptional(result, optional);
        result = populateNullable(result, nullable);
        result = populateWild(result, wild);
        result = populateWildest(result, wildest);

        return result;
    }

    private List<String> getRequiredKeys() {
        final List<String> allMatches = new ArrayList<>();
        final Matcher m = requiredPattern.matcher(url);

        while (m.find()) {
            allMatches.add(m.group(1));
        }

        return allMatches;
    }

    private List<String> getOptionalKeys() {
        final List<String> allMatches = new ArrayList<>();
        final Matcher m = optionalPattern.matcher(url);

        while (m.find()) {
            for (final String part : m.group(1).split(",")) {
                allMatches.add(part);
            }
        }

        return allMatches;
    }

    private List<String> getNullableKeys() {
        final List<String> allMatches = new ArrayList<>();
        final Matcher m = nullablePattern.matcher(url);

        while (m.find()) {
            allMatches.add(m.group(1));
        }

        return allMatches;
    }

    private List<String> getWildKeys() {
        final List<String> allMatches = new ArrayList<>();
        final Matcher m = wildPattern.matcher(url);

        while (m.find()) {
            allMatches.add(m.group(1));
        }

        return allMatches;
    }

    private List<String> getWildestKeys() {
        final List<String> allMatches = new ArrayList<>();
        final Matcher m = wildestPattern.matcher(url);

        while (m.find()) {
            allMatches.add(m.group(1));
        }

        return allMatches;
    }

    private List<String> getMissing(final List<String> required, final List<String> provided) {
        final List<String> missing = new ArrayList<>();

        for (final String key : required) {
            if (!provided.contains(key)) {
                missing.add(key);
            }
        }

        return missing;
    }

    private String populateRequired(final String url, final List<String> required) {
        String result = url;

        for (final String key : required) {
            result = result.replaceAll("\\{" + key + "\\}", parameters.get(key));
        }

        return result;
    }

    private String populateOptional(final String url, final List<String> optional) {
        String result = url;

        final Matcher m = optionalPattern.matcher(url);
        while (m.find()) {
            final String foundComplete = m.group(0);
            final String foundGrouped = m.group(1);
            final List<String> populated = new ArrayList<>();
            final List<String> allMatches = new ArrayList<>();

            for (final String part : foundGrouped.split(",")) {
                allMatches.add(part);
            }
            for (final String key : optional) {
                if (allMatches.indexOf(key) != -1) {
                    if (parameters.containsKey(key)) {
                        populated.add(key + "=" + parameters.get(key));
                    }
                }
            }

            final String joined = join("&", populated);
            final int startsAt = result.indexOf(foundComplete);
            final int firstQuestion = result.indexOf("?");
            if (joined.length() == 0) {
                result = result.replace(foundComplete, "");
            } else if (firstQuestion == -1 || startsAt < firstQuestion) {
                result = result.replace(foundComplete, "?" + joined);
            } else {
                result = result.replace(foundComplete, "&" + joined);
            }
        }

        return result;
    }

    private String populateNullable(final String url, final List<String> nullable) {
        String result = url;

        for (final String key : nullable) {
            if (!parameters.containsKey(key)) {
                result = result.replaceAll("\\{@" + key + "\\}", "");
            } else {
                result = result.replaceAll("\\{@" + key + "\\}", parameters.get(key));
            }
        }

        return result;
    }

    private String populateWild(final String url, final List<String> wild) {
        String result = url;

        for (final String key : wild) {
            if (!parameters.containsKey(key)) {
                result = result.replaceAll("\\{\\*" + key + "\\}", "");
            } else {
                result = result.replaceAll("\\{\\*" + key + "\\}", parameters.get(key));
            }
        }

        return result;
    }

    private String populateWildest(final String url, final List<String> wildest) {
        String result = url;

        for (final String key : wildest) {
            if (!parameters.containsKey(key)) {
                result = result.replaceAll("\\{\\!" + key + "\\}", "");
            } else {
                result = result.replaceAll("\\{\\!" + key + "\\}", parameters.get(key));
            }
        }

        return result;
    }

    private String join(final String delimiter, final List<String> collection) {
        final StringBuilder stringBuilder = new StringBuilder();
        final int size = collection.size();
        for (int i = 0; i < size; ++i) {
            if (i > 0) {
                stringBuilder.append(delimiter);
            }
            stringBuilder.append(collection.get(i));
        }
        return stringBuilder.toString();
    }
}
