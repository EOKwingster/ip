package com.eokwingster.responsors;

import com.eokwingster.data.ChatData;
import com.eokwingster.data.Task;

import java.util.List;

public class AddTaskResponsor implements Responsor {
    /**
     * store the user inputs as task
     * @param input The String message that user inputs
     * @return task added message
     */
    @Override
    public List<String> response(String input, ChatData chatData) {
        chatData.addTask(new Task(input));
        return List.of("added: " + input);
    }
}
