package com.eokwingster.responsor;

import com.eokwingster.Wee;
import com.eokwingster.util.DynamicMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Response output by Responsors
 *
 * @param messages Strings that will be printed
 * @param commandN the number of commands have been processed to output this response
 * @param tags     set of tags that label special status of responses
 */
public record Response(List<DynamicMessage> messages, int commandN, Set<Tag> tags) {
    public Response(List<DynamicMessage> messages, int commandN, Set<Tag> tags) {
        this.messages = List.copyOf(messages);
        this.commandN = commandN;
        this.tags = Set.copyOf(tags);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<DynamicMessage> messages;
        private int commandN;
        private Set<Tag> tags;

        private Builder() {
            messages = new ArrayList<>();
            commandN = 0;
            tags = new HashSet<>();
        }

        /**
         * increase the number of command executed by one
         * @return builder of this response
         */
        public Builder withNextCommandN() {
            commandN++;
            return this;
        }

        /**
         * append a list of dynamic messages at the end of messages
         * @return builder of this response
         */
        public Builder appendDynamicMessages(List<DynamicMessage> messages) {
            this.messages.addAll(messages);
            return this;
        }

        /**
         * append a list of string as a list of dynamic messages at the end of messages
         * @return builder of this response
         */
        public Builder appendMessages(List<String> messages) {
            return appendDynamicMessages(messages.stream().map(DynamicMessage::new).toList());
        }

        /**
         * append one or more string as dynamic message at the end of messages
         * @return builder of this response
         */
        public Builder appendMessages(String... messages) {
            return appendMessages(List.of(messages));
        }

        /**
         * append a formatted string as a dynamic message at the end of messages
         * @return builder of this response
         */
        public Builder appendMessage(String format, Object... args) {
            this.messages.add(new DynamicMessage(format, args));
            return this;
        }

        /**
         * format a string to warning message and append it as dynamic message at the end of messages
         * @return builder of this response
         */
        public Builder appendWarning(String format, Object... args) {
            return appendMessage("!!Warning: " + format, args);
        }

        /**
         * add a tag to tags
         * @return builder of this response
         */
        public Builder addTags(Tag... tags) {
            this.tags.addAll(List.of(tags));
            return this;
        }

        /**
         * build this builder into a response
         * @return response built
         */
        public Response build() {
            return new Response(messages, commandN, tags);
        }
    }

    /**
     * Formats and prints messages line by line to the console.
     * The first line will have chatbot name
     * Every line will have a vertical line before contents.
     */
    public void say() {
        String firstLine = Wee.NAME + ": ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            String prefix = (i == 0 ? firstLine : " ".repeat(firstLine.length())) + "| ";
            stringBuilder.append(prefix).append(String.format("%s", messages.get(i))).append("\n");
        }
        System.out.println(stringBuilder);
    }

    public enum Tag {
        Exit
    }
}
