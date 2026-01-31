package com.eokwingster.responsor.responsors;

import java.time.LocalDateTime;
import java.util.List;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.HasBeginTime;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Modifier responsor that setting a beginning time for a task
 */
public class TaskBeginTimeResponsor implements Responsor {
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse,
            List<Step> steps) {
        HasBeginTime task = (HasBeginTime) chatData.getFocusingTask();
        task.setBeginTime(LocalDateTime.parse(argument, ChatData.DATE_TIME_SAVE_FORMATTER));
        return builtResponse;
    }
}
