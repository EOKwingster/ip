package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.HasBeginTime;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class TaskBeginTimeResponsor implements Responsor {
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        HasBeginTime task = (HasBeginTime) chatData.getFocusingTask();
        task.setBeginTime(argument);
        return builtResponse;
    }
}
