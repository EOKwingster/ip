package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.response.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class ErrorResponsor implements Responsor {
    private final String errorMessage;

    public ErrorResponsor(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        return builtResponse.appendMessage(errorMessage);
    }
}
