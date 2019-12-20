package org.ranapat.hal;

public class HalNullPointerException extends HalException {
    public HalNullPointerException() {
        super();
    }

    @Override
    public String toString() {
        return "HalNullPointerException{" +
                '}';
    }

    @Override
    public String getMessage() {
        return "Cannot handle null strings";
    }
}
