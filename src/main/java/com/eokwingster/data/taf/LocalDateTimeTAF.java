package com.eokwingster.data.taf;

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
import java.time.LocalDateTime;

public class LocalDateTimeTAF implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (LocalDateTime.class != (typeToken.getRawType())) {
            return null;
        }

        return (TypeAdapter<T>) new TypeAdapter<LocalDateTime>() {

            @Override
            public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", localDateTime.getYear());
                jsonObject.addProperty("month", localDateTime.getMonthValue());
                jsonObject.addProperty("day", localDateTime.getDayOfMonth());
                jsonObject.addProperty("hour", localDateTime.getHour());
                jsonObject.addProperty("minute", localDateTime.getMinute());

                gson.toJson(jsonObject, jsonWriter);
            }

            @Override
            public LocalDateTime read(JsonReader jsonReader) throws IOException {
                JsonElement element = JsonParser.parseReader(jsonReader);
                JsonObject jsonObject = element.getAsJsonObject();

                int year = jsonObject.get("date").getAsInt();
                int month = jsonObject.get("month").getAsInt();
                int day = jsonObject.get("day").getAsInt();
                int hour = jsonObject.get("hour").getAsInt();
                int minute = jsonObject.get("minute").getAsInt();

                return LocalDateTime.of(year, month, day, hour, minute);
            }
        };
    }
}
