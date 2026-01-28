package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.HasEndTime;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.time.LocalDateTime;
import java.util.List;

public class TaskEndTimeResponsor implements Responsor {
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        HasEndTime task = (HasEndTime) chatData.getFocusingTask();
        task.setEndTime(LocalDateTime.parse(argument, ChatData.DATE_TIME_SAVE_FORMATTER));
        return builtResponse;
    }
}
