package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;

import java.util.ArrayList;
import java.util.List;

public class InputsListResponsor implements Responsor {
    /**
     * list the stored inputs with number labeling
     * @param input The String message that user inputs
     * @param chatData the data stored in current chat
     * @return list of stored inputs with number labeling
     */
    @Override
    public List<String> response(String input, ChatData chatData) {
        List<String> response = new ArrayList<>();
        List<String> inputsReceived = chatData.getInputsReceived();
        for (int i = 0; i < inputsReceived.size(); i++) {
            response.add((i + 1) + ". " + inputsReceived.get(i));
        }
        return response;
    }
}
