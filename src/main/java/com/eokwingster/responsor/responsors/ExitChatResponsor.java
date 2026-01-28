package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class ExitChatResponsor implements Responsor {
    /**
     * @return the exit message and exit tag
     */
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        return builtResponse.appendMessages("Bye. Hope to see you again soon!").addTags(Response.Tag.Exit);
    }
}
