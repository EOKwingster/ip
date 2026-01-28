package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class TaskDeleteResponsor implements Responsor {
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        builtResponse.appendMessages(
                "This task has been removed:",
                "  " + chatData.getTasks().get(Integer.parseInt(argument)),
                "Now you have " + chatData.getTasks().size() + " tasks."
        );
        return builtResponse;
    }
}
