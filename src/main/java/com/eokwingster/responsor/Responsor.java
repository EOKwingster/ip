package com.eokwingster.responsor;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;

import java.util.List;

/**
 * define the universal response behavior。
 * Any response logic should be inside a class implements this interface.
 */
public interface Responsor {
    /**
     * The response logic of this Responsor
     * @param argument the argument after keyword
     * @param chatData the data stored in current chat
     * @param builtResponse the response given by previous responsors in this command line
     * @param steps list of steps inside user input
     * @return The builder of the response for this command line.
     * @see Response
     */
    Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps);
}
