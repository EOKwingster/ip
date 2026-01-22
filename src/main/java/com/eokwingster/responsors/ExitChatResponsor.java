package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;

public class ExitChatResponsor implements Responsor {

    /**
     * @return the exit message
     */
    @Override
    public Response response(String input, ChatData chatData) {
        return Response.of("Bye. Hope to see you again soon!", 1);
    }
}
