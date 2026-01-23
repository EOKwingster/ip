package com.eokwingster.responsor.responsors;

import com.eokwingster.Wee;
import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class StartChatResponsor implements Responsor {
    /**
     * start a new chat
     * @return the chat start message
     */
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        chatData.reset();
        return builtResponse.appendMessages("Hello! I'm " + Wee.NAME, "What can I do for you?");
    }
}
