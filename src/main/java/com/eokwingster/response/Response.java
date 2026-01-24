package com.eokwingster.response;

import com.eokwingster.Wee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Response output by Responsors
 * @param messages Strings that will be printed
 * @param commandN the number of commands have been processed to output this response
 * @param tags set of tags that label special status of responses
 */
public record Response(List<DynamicMessage> messages, List<Integer> stepStartPoints, int commandN, Set<Tag> tags) {
    public Response(List<DynamicMessage> messages, List<Integer> stepStartPoints, int commandN, Set<Tag> tags) {
        this.messages = List.copyOf(messages);
        this.stepStartPoints = stepStartPoints;
        this.commandN = commandN;
        this.tags = Set.copyOf(tags);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<DynamicMessage> messages;
        private List<Integer> stepStartPoints;
        private int commandN;
        private Set<Tag> tags;

        private Builder() {
            messages = new ArrayList<>();
            stepStartPoints = new ArrayList<>();
            commandN = 0;
            tags = new HashSet<>();
        }

        public Builder withNextCommandN() {
            commandN++;
            return this;
        }

        public Builder withMessages(List<String> messages) {
            this.messages = new ArrayList<>(messages.stream().map(DynamicMessage::new).toList());
            return this;
        }

        public Builder withMessages(String... messages) {
            return withMessages(List.of(messages));
        }

        public Builder withMessage(String format, Object... args) {
            this.messages = new ArrayList<>();
            this.messages.add(new DynamicMessage(format, args));
            return this;
        }

        public Builder appendMessages(List<String> messages) {
            this.messages.addAll(messages.stream().map(DynamicMessage::new).toList());
            return this;
        }

        public Builder appendMessages(String... messages) {
            return appendMessages(List.of(messages));
        }

        public Builder appendMessage(String format, Object... args) {
            this.messages.add(new DynamicMessage(format, args));
            return this;
        }

        public Builder withMessageAt(int i, String format, Object... args) {
            this.messages.set(i, new DynamicMessage(format, args));
            return this;
        }

        public Builder withMessageAtLast(String format, Object... args) {
            return withMessageAt(stepStartPoints.size() - 1, format, args);
        }

        public Builder addTags(Tag... tags) {
            this.tags.addAll(List.of(tags));
            return this;
        }

        public Builder removeTags(Tag... tags) {
            List.of(tags).forEach(this.tags::remove);
            return this;
        }

        public boolean hasTags(Tag... tags) {
            return this.tags.containsAll(List.of(tags));
        }

        /**
         * mark next line of message the start point of a step
         */
        public void markStepStartPoint() {
            this.stepStartPoints.add(messages.size());
        }

        public void removeLastStepStartPoint() {
            this.stepStartPoints.remove(stepStartPoints.size() - 1);
        }

        public Response build() {
            return new Response(messages, stepStartPoints, commandN, tags);
        }
    }

    /**
     * Formats and prints messages line by line to the console.
     * The first line will have chatbot name
     * Every line will have a vertical line before all marks.
     * Evert start line of a step will have a ">>" mark.
     */
    public void say() {
        String stepStartPoint = ">>";
        String firstLine = Wee.NAME + ": ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            String prefix = "";
            prefix += i == 0 ? firstLine : " ".repeat(firstLine.length());
            prefix += "|";
            prefix += stepStartPoints.contains(i) ? stepStartPoint : " ".repeat(stepStartPoint.length());
            prefix += " ";
            stringBuilder.append(prefix).append(String.format("%s", messages.get(i))).append("\n");
        }
        System.out.println(stringBuilder);
    }

    public enum Tag {
        Exit,
        Final,
        Modifier
    }
}
