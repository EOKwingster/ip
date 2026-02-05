package com.eokwingster.server;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Consumer;

import com.eokwingster.command.CommandParser;
import com.eokwingster.command.CommandRegistry;
import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.Storage;
import com.eokwingster.responsor.Response;

/**
 * Class to handle all internal logic
 */
public class Server {
    private Consumer<Response> responseSender;
    private final ChatData chatData;
    private final Storage storage;

    /**
     * Construct members.
     */
    public Server() {
        this.chatData = new ChatData();
        this.storage = new Storage();
    }

    /**
     * Set up server.
     * @throws IOException
     * @throws URISyntaxException
     */
    public void setUp() throws IOException, URISyntaxException {
        storage.load(chatData);
    }

    /**
     * Handle the input sent from gui side.
     * @param input
     */
    public void handleInput(String input) {
        Response response = getResponseFromInput(input);
        if (response.tags().contains(Response.Tag.SAVE)) {
            try {
                storage.save(chatData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        responseSender.accept(response);
    }

    /**
     * Run the input through responsor pipeline to get a response
     * @param input user input
     * @return Response of this input
     */
    private Response getResponseFromInput(String input) {
        List<Step> steps;
        Response.Builder response = Response.builder();
        try {
            steps = CommandParser.getStepsFromInput(input, chatData);
        } catch (IllegalArgumentException e) {
            return response.appendWarning(e.getMessage()).build();
        }
        return steps.stream()
                .reduce(response, (builtResponse, step) -> {
                    return CommandRegistry.getResponsor(step.keyword())
                            .response(step.argument(), chatData, builtResponse);
                }, (r1, r2) -> r2)
                .build();
    }

    public void setClientResponseHandler(Consumer<Response> responseSender) {
        this.responseSender = responseSender;
    }
}
