package com.eokwingster.data;

import com.eokwingster.data.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class store all the data needed and generated in a chat.
 */
public class ChatData {
    public static final DateTimeFormatter DATE_TIME_SAVE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-]MM-dd[ HH:mm]")
            .parseDefaulting(ChronoField.YEAR, LocalDateTime.now().getYear())
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .toFormatter(Locale.US);
    public static final DateTimeFormatter DATE_TIME_DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy MMM dd hh:mm a", Locale.US);

    private final List<Task> tasks = new ArrayList<>();
    private int focusingTaskIndex = -1;

    public Task getFocusingTask() {
        return tasks.get(focusingTaskIndex);
    }

    public void setFocusingTaskIndex(int focusingTaskIndex) {
        this.focusingTaskIndex = focusingTaskIndex;
    }

    /**
     * Add a task into tasks
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        setFocusingTaskIndex(tasks.size() - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Reset chat data to original state
     */
    public void reset() {
        tasks.clear();
        focusingTaskIndex = -1;
    }

    public void copy(ChatData data) {
        this.tasks.clear();
        this.tasks.addAll(data.tasks);
    }
}
