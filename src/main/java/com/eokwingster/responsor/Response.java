package com.eokwingster.responsor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.Task;
import com.eokwingster.util.DynamicMessage;

/**
 * Response output by Responsors
 *
 * @param messages Strings that will be printed
 * @param stepN the number of commands have been processed to output this response
 * @param tags     set of tags that label special status of responses
 */
public record Response(List<DynamicMessage> messages, int stepN, Set<Tag> tags) {

    /**
     * This constructor is an insurance for preventing this response from containing any mutable collection.
     * A response should be obtained from builder in most of the cases.
     * @param messages a list of dynamic messages
     * @param stepN the number of steps have been executed on this response
     * @param tags list of tags
     * @see Builder
     * @see com.eokwingster.command.Step
     * @see Tag
     */
    public Response(List<DynamicMessage> messages, int stepN, Set<Tag> tags) {
        this.messages = List.copyOf(messages);
        this.stepN = stepN;
        this.tags = Set.copyOf(tags);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (DynamicMessage message : messages) {
            stringBuilder.append(message).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Builder of response, for handling frequent minor edits, will be pass through the responsor pipeline
     */
    public static class Builder {
        private List<DynamicMessage> messages;
        private int stepN;
        private Set<Tag> tags;

        private Builder() {
            messages = new ArrayList<>();
            stepN = 0;
            tags = new HashSet<>();
        }

        /**
         * increase the number of command executed by one
         * @return builder of this response
         */
        public Builder withNextStepN() {
            stepN++;
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
         * append the messages that list all tasks in chatData
         * @param chatData data stored by this chat
         * @return builder of this response
         * @see ChatData
         */
        public Builder appendTasks(ChatData chatData) {
            return appendTasksConditional(chatData, task -> true);
        }

        /**
         * append the messages that list tasks in chatData that return true in a predicate
         * @param chatData data stored by this chat
         * @param predicate receive a task and return a boolean
         * @return builder of this response
         * @see ChatData
         */
        public Builder appendTasksConditional(ChatData chatData, Predicate<Task> predicate) {
            for (int i = 0; i < chatData.getTaskCount(); i++) {
                Task task = chatData.getTaskAt(i);
                if (predicate.test(task)) {
                    appendMessages((i + 1) + "." + chatData.getTaskAt(i));
                }
            }
            return this;
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
            return new Response(messages, stepN, tags);
        }
    }

    /**
     * Tags for labeling special cases. Special responsors will add corresponding tags into list of tags in response
     */
    public enum Tag {
        EXIT,
        SAVE
    }
}
