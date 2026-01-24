package com.eokwingster.responsor;

import com.eokwingster.Wee;
import com.eokwingster.util.DynamicMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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

        public Builder appendDynamicMessages(List<DynamicMessage> messages) {
            this.messages.addAll(messages);
            return this;
        }

        public Builder appendMessages(List<String> messages) {
            return appendDynamicMessages(messages.stream().map(DynamicMessage::new).toList());
        }

        public Builder appendMessages(String... messages) {
            return appendMessages(List.of(messages));
        }

        public Builder appendMessage(String format, Object... args) {
            this.messages.add(new DynamicMessage(format, args));
            return this;
        }

        public Builder appendWarnings(List<String> warnings) {
            return appendMessages(IntStream
                    .range(0, warnings.size())
                    .boxed()
                    .map(i -> (i == 0 ? "!!Warning: " : "           ") + warnings.get(i))
                    .toList());
        }

        public Builder appendWarning(String format, Object... args) {
            return appendMessage("!!Warning: " + format, args);
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
        public Builder markStepStartPoint() {
            this.stepStartPoints.add(messages.size());
            return this;
        }

        public Builder removeLastStepStartPoint() {
            this.stepStartPoints.remove(stepStartPoints.size() - 1);
            return this;
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
