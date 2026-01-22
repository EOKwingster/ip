package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskListResponsor implements Responsor {
    /**
     * list the stored tasks with number labeling
     * @param input The String message that user inputs
     * @param chatData the data stored in current chat
     * @return list of stored tasks with number labeling
     */
    @Override
    public Response response(String input, ChatData chatData) {
        List<String> messages = new ArrayList<>();
        List<Task> tasks = chatData.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            messages.add((i + 1) + ". " + tasks.get(i));
        }
        return Response.of(messages);
    }
}
