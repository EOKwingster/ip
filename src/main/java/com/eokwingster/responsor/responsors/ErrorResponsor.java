package com.eokwingster.responsor.responsors;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.Responsor;

import java.util.List;

public class ErrorResponsor implements Responsor {
    private final List<String> errorMessages;

    private ErrorResponsor(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public static ErrorResponsor of(String... errorMessages) {
        return new ErrorResponsor(List.of(errorMessages));
    }

    @Override
    public Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps) {
        return builtResponse.appendWarnings(errorMessages);
    }
}
