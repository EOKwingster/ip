package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class UnknownResponsor implements Responsor {
    private final String unknownKeyword;
    public UnknownResponsor(String unknownKeyword) {
        this.unknownKeyword = unknownKeyword;
    }

    /**
     * @return Unregistered keyword message
     */
    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        return builtResponse.appendMessages("I don't understand: " + String.join(" ", unknownKeyword, argument), "What are you talking about?");
    }
}
