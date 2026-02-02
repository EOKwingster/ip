package com.eokwingster.responsor.responsors;

import java.util.List;

import com.eokwingster.command.Step;
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
            Response.Builder builtResponse,
            List<Step> steps) {
        return builtResponse.appendMessage("Tasks in your list matching: " + argument)
                .appendTasksConditional(
                        chatData,
                    task -> task.ifDescriptionContains(argument)
                );
    }
}
