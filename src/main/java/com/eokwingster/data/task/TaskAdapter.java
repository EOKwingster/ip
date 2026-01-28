package com.eokwingster.data.task;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TaskAdapter extends TypeAdapter<Task> {
    private final Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, Task task) throws IOException {
        JsonObject jsonObject = gson.toJsonTree(task).getAsJsonObject();
        String type = task.type().name();
        jsonObject.addProperty("type", type);
        gson.toJson(jsonObject, out);
    }

    @Override
    public Task read(JsonReader in) throws IOException {
        JsonObject jsonObject = JsonParser.parseReader(in).getAsJsonObject();
        TaskType type = TaskType.valueOf(jsonObject.get("type").getAsString());
        return switch (type) {
            case TO_DO -> gson.fromJson(jsonObject, ToDo.class);
            case DEADLINE -> gson.fromJson(jsonObject, Deadline.class);
            case EVENT -> gson.fromJson(jsonObject, Event.class);
        };
    }
}
