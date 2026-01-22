package com.eokwingster.responsors;

import com.eokwingster.Wee;

import java.util.List;

/**
 * Response output by Responsors
 * @param messages Strings that will be printed
 * @param tag for special case (i.e.exit chat)
 */
public record Response(List<String> messages, int tag) {
    public static Response of(List<String> messages, int tag) {
        return new Response(messages, tag);
    }

    public static Response of(List<String> messages) {
        return of(messages, 0);
    }

    public static Response of(String message,  int tag) {
        return of(List.of(message), tag);
    }

    public static Response of(String message) {
        return of(message, 0);
    }

    /**
     * Formats and prints messages line by line to the console with the bot name in front of the first line
     */
    public void say() {
        String speaker = Wee.NAME + " >> ";
        String indentation = " ".repeat(speaker.length());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            String prefix = i == 0 ? speaker : indentation;
            stringBuilder.append(prefix).append(messages.get(i)).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
