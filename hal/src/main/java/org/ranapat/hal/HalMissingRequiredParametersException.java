package org.ranapat.hal;

import java.util.List;

public class HalMissingRequiredParametersException extends HalException {
    private final List<String> missingKeys;

    public HalMissingRequiredParametersException(final List<String> keys) {
        super();

        missingKeys = keys;
    }

    public List<String> getMissingKeys() {
        return missingKeys;
    }

    @Override
    public String toString() {
        return "HalMissingRequiredParametersException{" +
                "missingKeys=" + join(missingKeys) +
                '}';
    }

    @Override
    public String getMessage() {
        return "Missing required keys: " + join(missingKeys);
    }

    private String join(List<String> keys) {
        final StringBuilder result = new StringBuilder();
        final int length = keys.size();
        int index = 0;

        for (final String key : keys) {
            result
                    .append(key)
                    .append(++index < length ? "," : "");
        }

        return result.toString();
    }
}
