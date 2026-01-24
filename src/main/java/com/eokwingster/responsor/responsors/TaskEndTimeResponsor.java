package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Keyword;
import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.HasEndTime;
import com.eokwingster.response.Response;
import com.eokwingster.responsor.Modifier;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class TaskEndTimeResponsor implements Modifier, Responsor {
    @Override
    public List<Keyword> getRootKeywords() {
        return List.of(Keyword.DEADLINE, Keyword.EVENT);
    }

    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        HasEndTime task = (HasEndTime) chatData.getFocusingTask();
        task.setEndTime(argument);
        return builtResponse;
    }
}
