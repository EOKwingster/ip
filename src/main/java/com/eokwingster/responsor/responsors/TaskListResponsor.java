package com.eokwingster.responsor.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that list all tasks in chat data
 */
public class TaskListResponsor implements Responsor {
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse) {
        return builtResponse.appendMessages("Task in your list:")
                .appendTasks(chatData);
    }
}
