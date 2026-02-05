package com.eokwingster.responsor.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that handle task searching.
 */
public class TasksFindResponsor implements Responsor {
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse) {
        return builtResponse.appendMessage("Tasks in your list matching: " + argument)
                .appendTasksConditional(
                        chatData,
                    task -> task.ifDescriptionContains(argument)
                );
    }
}
