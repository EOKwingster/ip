package com.eokwingster.responsor.responsors;

import java.util.List;

import com.eokwingster.command.Step;
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
            Response.Builder builtResponse,
            List<Step> steps) {
        builtResponse.appendMessages("Task in your list:");
        for (int i = 0; i < chatData.getTaskCount(); i++) {
            builtResponse.appendMessages((i + 1) + "." + chatData.getTaskAt(i));
        }
        return builtResponse;
    }
}
