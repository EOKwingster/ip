package com.eokwingster.responsor.responsors;

import java.util.List;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that clear all tasks in chat data
 */
public class TaskClearResponsor implements Responsor {
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse,
            List<Step> steps) {
        chatData.clearTasks();
        return builtResponse.appendMessage("All tasks have been cleared").addTags(Response.Tag.SAVE);
    }
}
