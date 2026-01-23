package com.eokwingster.responsor;

import com.eokwingster.Wee;
import com.eokwingster.data.ChatData;

import java.util.List;

/**
 * define the universal response behavior。
 * Any response logic should be inside a class implements this interface.
 */
public interface Responsor {
    /**
     * The response logic of this Responsor
     * @param steps List of steps inside user input
     * @param chatData the data stored in current chat
     * @param preResponse the response given by previous responsors
     * @return Response messages as a list of String, each element is one line.
     */
    Response response(List<Wee.Step> steps, ChatData chatData, Response preResponse);
}
