package com.eokwingster.responsor;

import com.eokwingster.data.ChatData;

/**
 * define the universal response behavior。
 * Any response logic should be inside a class implements this interface.
 */
public interface Responsor {
    /**
     * The processing logic of this responsor, includes following behavior:
     * 1. Edit generated messages
     * 2. Add tags
     * 3. do some operations on chat data
     *
     * @param argument      the argument after keyword
     * @param chatData      the data stored in current chat
     * @param builtResponse the response given by previous responsors in this command line
     * @return The builder of the response for this command line.
     * @see Response
     */
    Response.Builder response(
            String argument,
            ChatData chatData,
            Response.Builder builtResponse);
}
