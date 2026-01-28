package com.eokwingster.data.taf;

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

import java.io.IOException;

public class TaskTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (!Task.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }

        return (TypeAdapter<T>) new TypeAdapter<Task>() {
            @Override
            public void write(JsonWriter out, Task value) throws IOException {
                TypeAdapter<Task> delegate = (TypeAdapter<Task>) gson.getDelegateAdapter(
                        TaskTypeAdapterFactory.this, TypeToken.get(value.getClass()));

                JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();
                jsonObject.addProperty("type", value.type().name());

                gson.toJson(jsonObject, out);
            }

            @Override
            public Task read(JsonReader in) throws IOException {
                JsonElement element = JsonParser.parseReader(in);
                JsonObject jsonObject = element.getAsJsonObject();

                String typeStr = jsonObject.get("type").getAsString();
                TaskType taskType = TaskType.valueOf(typeStr);

                Class<? extends Task> targetClass = switch (taskType) {
                    case TO_DO -> ToDo.class;
                    case DEADLINE -> Deadline.class;
                    case EVENT -> Event.class;
                };

                return gson.getDelegateAdapter(TaskTypeAdapterFactory.this, TypeToken.get(targetClass))
                        .fromJsonTree(jsonObject);
            }
        };
    }
}
