package com.eokwingster.responsors;

import java.util.List;

public class EchoResponsor implements Responsor {

    /**
     * response the exact same message with user inputs
     * @param input The String message that user inputs
     * @return user inputs
     */
    @Override
    public List<String> response(String input) {
        return List.of(input);
    }
}
