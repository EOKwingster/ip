package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;

public class ExitChatResponsor implements Responsor {

    /**
     * @return the exit message and exit tag
     */
    @Override
    public Response response(String input, ChatData chatData) {
        return Response.of("Bye. Hope to see you again soon!", 1);
    }
}
