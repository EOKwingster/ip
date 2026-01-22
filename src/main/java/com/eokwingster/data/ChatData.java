package com.eokwingster.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class store all the data needed and generated in a chat.
 */
public class ChatData {
    private final List<String> inputsReceived = new ArrayList<>();

    public void storeInput(String input) {
        inputsReceived.add(input);
    }

    public List<String> getInputsReceived() {
        return inputsReceived;
    }
}
