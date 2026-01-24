package com.eokwingster.util;

import java.util.Formattable;
import java.util.Formatter;

public record DynamicMessage(String format, Object... args) implements Formattable {

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(format, args);
    }
}
