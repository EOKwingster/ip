package com.eokwingster.responsor;

import com.eokwingster.Wee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Response output by Responsors
 * @param messages Strings that will be printed
 * @param commandN the number of commands have been processed to output this response
 * @param tags list of tags that label special status of responses
 */
public record Response(List<String> messages, int commandN, List<Tag> tags) {
    public Response(List<String> messages, int commandN, List<Tag> tags) {
        this.messages = List.copyOf(messages);
        this.commandN = commandN;
        this.tags = List.copyOf(tags);
    }

    public static Response of(List<String> messages, int commandN, List<Tag> tags) {
        return new Response(messages, commandN, tags);
    }

    public static Response of() {
        return of(List.of(), 0, List.of());
    }

    public Response update() {
        return of(messages, commandN + 1, tags);
    }

    public Response update(Tag... tags) {
        return of(messages, commandN, Stream.concat(this.tags.stream(), Arrays.stream(tags)).toList());
    }

    public Response replace(List<String> messages) {
        return of(messages, commandN, tags);
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

    public enum Tag {
        Exit,
        Final
    }
}
