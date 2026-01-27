package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;
import com.eokwingster.command.ArgumentParser;

import java.util.List;

public class TaskDeleteResponsor implements Responsor {
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        ArgumentParser.parseTaskInt(argument, chatData, builtResponse)
                .ifPresent(i -> {
                    builtResponse.appendMessages(
                            "This task has been removed:",
                            "  " + chatData.getTasks().get(i),
                            "Now you have " + chatData.getTasks().size() + " tasks."
                    );
                });
        return builtResponse;
    }
}
