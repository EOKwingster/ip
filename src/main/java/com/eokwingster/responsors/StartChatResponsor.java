package com.eokwingster.responsors;

import com.eokwingster.Wee;
import com.eokwingster.data.ChatData;

import java.util.List;

public class StartChatResponsor implements Responsor {
    /**
     * @return the chat start message
     */
    @Override
    public Response response(String input, ChatData chatData) {
        return Response.of(List.of("Hello! I'm " + Wee.NAME, "What can I do for you?"));
    }
}
