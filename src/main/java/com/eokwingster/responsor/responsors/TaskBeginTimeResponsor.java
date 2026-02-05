package com.eokwingster.responsor.responsors;

import java.time.LocalDateTime;

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
            Response.Builder builtResponse) {
        HasBeginTime task = (HasBeginTime) chatData.getFocusingTask();
        task.setBeginTime(LocalDateTime.parse(argument, ChatData.DATE_TIME_SAVE_FORMATTER));
        return builtResponse;
    }
}
