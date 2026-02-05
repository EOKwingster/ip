package com.eokwingster.responsor.responsors;

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
            Response.Builder builtResponse) {
        chatData.clearTasks();
        return builtResponse.appendMessage("All tasks have been cleared").addTags(Response.Tag.SAVE);
    }
}
