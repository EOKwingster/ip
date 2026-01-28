package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.HasEndTime;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class TaskEndTimeResponsor implements Responsor {
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        HasEndTime task = (HasEndTime) chatData.getFocusingTask();
        task.setEndTime(argument);
        return builtResponse;
    }
}
