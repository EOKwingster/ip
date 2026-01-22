package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;

import java.util.List;

public class InputRecordResponsor implements Responsor {
    /**
     * store the user inputs into chat
     * @param input The String message that user inputs
     * @return inputs store message
     */
    @Override
    public List<String> response(String input, ChatData chatData) {
        chatData.storeInput(input);
        return List.of("added: " + input);
    }
}
