package com.eokwingster.command;

import java.util.Arrays;
import java.util.List;

public record Step(String keyword, String argument) {
    public static Step of(String command, String argument) {
        return new Step(command, argument);
    }

    public static Step of(String stepInput) {
        String[] step = stepInput.split(" ", 2);
        String command = step[0];
        String argument = step.length > 1 ? step[1] : "";
        return of(command, argument);
    }

    public static List<Step> listOf(String input) {
        return Arrays.stream(input.split(" /")).map(Step::of).toList();
    }
}
