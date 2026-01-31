package com.eokwingster.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

import com.eokwingster.data.taf.LocalDateTimeTaf;
import com.eokwingster.data.taf.TaskTaf;
import com.eokwingster.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Handles saving and loading of chat data
 */
public class Storage {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(new TaskTaf())
            .registerTypeAdapterFactory(new LocalDateTimeTaf())
            .setPrettyPrinting()
            .create();

    /**
     * save chat data into JSON file
     * @throws IOException
     * @throws URISyntaxException
     * @see Utils#getJarFolderPath()
     */
    public void save(ChatData chatData) throws IOException, URISyntaxException {
        String json = GSON.toJson(chatData);
        Files.writeString(Utils.getJarFolderPath(), json);
    }

    /**
     * load data from JSON file
     * @throws IOException
     * @throws URISyntaxException
     * @see Utils#getJarFolderPath()
     */
    public void load(ChatData chatData) throws IOException, URISyntaxException {
        try {
            String json = Files.readString(Utils.getJarFolderPath());
            ChatData data = GSON.fromJson(json, ChatData.class);
            chatData.copy(data);
        } catch (NoSuchFileException e) {
            chatData.reset();
        }
    }
}
