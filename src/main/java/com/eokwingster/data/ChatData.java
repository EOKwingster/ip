package com.eokwingster.data;

import com.eokwingster.data.task.Task;
import com.eokwingster.data.task.TaskAdapter;
import com.eokwingster.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class store all the data needed and generated in a chat.
 */
public class ChatData {
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Task.class, new TaskAdapter()).setPrettyPrinting().create();

    private final List<Task> tasks = new ArrayList<>();
    private int focusingTaskIndex = -1;

    public Task getFocusingTask() {
        return tasks.get(focusingTaskIndex);
    }

    public void setFocusingTaskIndex(int focusingTaskIndex) {
        this.focusingTaskIndex = focusingTaskIndex;
    }

    public void addTask(Task task) {
        tasks.add(task);
        setFocusingTaskIndex(tasks.size() - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void reset() {
        tasks.clear();
        focusingTaskIndex = -1;
    }

    public void save() throws IOException, URISyntaxException {
        String json = GSON.toJson(this);
        Files.writeString(Utils.getJarFolderPath(), json);
    }

    public void load() throws IOException, URISyntaxException {
        try {
            String json = Files.readString(Utils.getJarFolderPath());
            ChatData data = GSON.fromJson(json, ChatData.class);
            copy(data);
        } catch (NoSuchFileException e) {
            this.reset();
        }
    }

    private void copy(ChatData data) {
        this.tasks.clear();
        this.tasks.addAll(data.tasks);
    }
}
