package com.eokwingster;

import com.eokwingster.data.ChatData;
import com.eokwingster.responsors.*;

import java.util.List;
import java.util.Scanner;

public class Wee {
    public static final String NAME = "Wee";
    private final ResponsorRegistry responsorRegistry;
    private final ChatData chatData;

    private Wee(ResponsorRegistry responsorRegistry, ChatData chatData) {
        this.responsorRegistry = responsorRegistry;
        this.chatData = chatData;
    }

    public static void main(String[] args) {
        String logo = """
                ██     ██  ████████  ████████\s
                ██     ██  ██        ██      \s
                ██  █  ██  ██████    ██████  \s
                ██ ███ ██  ██        ██      \s
                 ███ ███   ████████  ████████
                """;
        System.out.println(logo);

        Wee wee = getDefaultWee();
        wee.getResponseFromInput("new").say();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            Response response = wee.getResponseFromInput(input);
            response.say();
            if (response.tag() == 1) {
                break;
            }
        }
    }

    /**
     * @param input user input
     * @return Response of this input
     */
    private Response getResponseFromInput(String input) {
        return responsorRegistry.getResponse(input, chatData);
    }

    /**
     * get Wee with default setting
     * @return the default Wee
     */
    private static Wee getDefaultWee() {
        ResponsorRegistry responsorRegistry = new ResponsorRegistry();
        responsorRegistry.register(List.of("new", "hi"), new StartChatResponsor());
        responsorRegistry.register(List.of("exit", "bye"), new ExitChatResponsor());
        responsorRegistry.register("add", new AddTaskResponsor());
        responsorRegistry.register("list", new TaskListResponsor());
        responsorRegistry.register("mark", new TaskDoneStatusResponsor(true));
        responsorRegistry.register("unmark", new TaskDoneStatusResponsor(false));
        return new Wee(responsorRegistry,  new ChatData());
    }
}
