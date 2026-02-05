package com.eokwingster.responsor.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

/**
 * Responsor that trigger exit of chat
 */
public class ExitChatResponsor implements Responsor {
    @Override
    public Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse) {
        return builtResponse.appendMessages("Bye. Hope to see you again soon!").addTags(Response.Tag.EXIT);
    }
}
