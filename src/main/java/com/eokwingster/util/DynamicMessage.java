package com.eokwingster.util;

import java.util.Formattable;
import java.util.Formatter;

/**
 * Stores format and args for lazy string formatting.
 * Pass DynamicMessgae object into String.format() as an argument of one %s flag to perform formatting.
 * @param format
 * @param args
 */
public record DynamicMessage(String format, Object... args) implements Formattable {
    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(format, args);
    }

    @Override
    public String toString() {
        return String.format("%s", this);
    }
}
