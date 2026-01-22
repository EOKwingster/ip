package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.Task;

public class AddTaskResponsor implements Responsor {
    /**
     * store the user inputs as task
     * @param description description of the task
     * @return task added message
     */
    @Override
    public Response response(String description, ChatData chatData) {
        chatData.addTask(new Task(description));
        return Response.of("Added: " + description);
    }
}
