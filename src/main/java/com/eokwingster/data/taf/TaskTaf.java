package com.eokwingster.data.taf;

import java.io.IOException;

import com.eokwingster.data.task.Deadline;
import com.eokwingster.data.task.Event;
import com.eokwingster.data.task.Task;
import com.eokwingster.data.task.TaskType;
import com.eokwingster.data.task.ToDo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * TypeAdapterFactory used to generate TypeAdapter of Task class
 */
public class TaskTaf implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (!Task.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }

        return (TypeAdapter<T>) new TypeAdapter<Task>() {
            @Override
            public void write(JsonWriter jsonWriter, Task task) throws IOException {
                TypeAdapter<Task> delegate = (TypeAdapter<Task>) gson.getDelegateAdapter(
                        TaskTaf.this, TypeToken.get(task.getClass()));

                JsonObject jsonObject = delegate.toJsonTree(task).getAsJsonObject();
                jsonObject.addProperty("type", task.getType().name());

                gson.toJson(jsonObject, jsonWriter);
            }

            @Override
            public Task read(JsonReader jsonReader) throws IOException {
                JsonElement element = JsonParser.parseReader(jsonReader);
                JsonObject jsonObject = element.getAsJsonObject();

                String typeStr = jsonObject.get("type").getAsString();
                TaskType taskType = TaskType.valueOf(typeStr);

                Class<? extends Task> targetClass = switch (taskType) {
                    case TO_DO -> ToDo.class;
                    case DEADLINE -> Deadline.class;
                    case EVENT -> Event.class;
                };

                return gson.getDelegateAdapter(TaskTaf.this, TypeToken.get(targetClass))
                        .fromJsonTree(jsonObject);
            }
        };
    }
}
