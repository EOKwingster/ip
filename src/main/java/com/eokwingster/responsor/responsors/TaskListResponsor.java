package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class TaskListResponsor implements Responsor {
    /**
     * @return list of stored tasks with number labeling
     */
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        builtResponse.appendMessages("Task in your list:");
        for (int i = 0; i < chatData.getTaskCount(); i++) {
            builtResponse.appendMessages((i + 1) + "." + chatData.getTaskAt(i));
        }
        return builtResponse;
    }
}
