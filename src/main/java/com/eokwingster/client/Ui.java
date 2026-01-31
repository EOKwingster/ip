package com.eokwingster.client;

import java.util.List;

import com.eokwingster.Wee;
import com.eokwingster.responsor.Response;
import com.eokwingster.util.DynamicMessage;

/**
 * This class contains the user interface functions
 */
public class Ui {
    private static final String SPEAKER_LABEL = Wee.NAME + ": ";
    private static final String INDENTATION = " ".repeat(SPEAKER_LABEL.length());
    private static final String LINE_PREFIX = "| ";

    /**
     * Formats and prints messages of a response.
     * The first line will have chatbot name label.
     * Every line will have a vertical line before contents.
     * @param response The response returned by responsors
     */
    public void display(Response response) {
        StringBuilder stringBuilder = new StringBuilder();
        List<DynamicMessage> messages = response.messages();
        for (int i = 0; i < messages.size(); i++) {
            String prefix = (i == 0 ? SPEAKER_LABEL : INDENTATION) + LINE_PREFIX;
            stringBuilder.append(String.format("%s%s", prefix, messages.get(i))).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
