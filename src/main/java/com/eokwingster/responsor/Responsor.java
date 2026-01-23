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
     * @return Response messages as a list of String, each element is one line.
     */
    Response.Builder response(String argument, ChatData chatData, Response.Builder builtResponse, List<Step> steps);
}
