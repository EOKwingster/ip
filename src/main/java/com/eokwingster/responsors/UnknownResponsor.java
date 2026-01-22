package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;

public class UnknownResponsor implements Responsor {
    @Override
    public Response response(String input, ChatData chatData) {
        return Response.of("What are you talking about?");
    }
}
