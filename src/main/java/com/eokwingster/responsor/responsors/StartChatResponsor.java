package com.eokwingster.responsor.responsors;

import com.eokwingster.Wee;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that trigger start chat
 */
public class StartChatResponsor implements Responsor {
    /**
     * start a new chat
     * @return the chat start message
     */
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse) {
        return builtResponse.appendMessages("Hello! I'm " + Wee.NAME, "What can I do for you?");
    }
}
