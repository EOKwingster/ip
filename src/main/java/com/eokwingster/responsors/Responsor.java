package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;

/**
 * define the universal response behavior。
 * Any response logic should be inside a class implements this interface.
 */
public interface Responsor {
    /**
     * The response logic of this Responsor
     * @param input The String message that user inputs
     * @param chatData the data stored in current chat
     * @return Response messages as a list of String, each element is one line.
     */
    Response response(String input, ChatData chatData);
}
