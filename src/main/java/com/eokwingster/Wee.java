package com.eokwingster;

import com.eokwingster.data.ChatData;
import com.eokwingster.responsors.ExitChatResponsor;
import com.eokwingster.responsors.Responsor;
import com.eokwingster.responsors.ResponsorRegistry;
import com.eokwingster.responsors.StartChatResponsor;

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
        wee.responseFromInput("new");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            Responsor responsor = wee.responsorRegistry.getResponsor(input);
            wee.responseFromInput(input);
            if (responsor.willExit()) {
                break;
            }
        }
    }

    /**
     * Call the corresponding response logic from user inputs. Default response is echo.
     * @param input the user inputs
     */
    private void responseFromInput(String input) {
        say(responsorRegistry.getResponsor(input).response(input, chatData));
    }

    /**
     * Formats and prints messages line by line to the console with the name of this bot in front of the first line
     * @param messages One or more strings to be printed as part of the dialogue.
     */
    private void say(List<String> messages) {
        String speaker = NAME + " >> ";
        String indentation = " ".repeat(speaker.length());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            String prefix = i == 0 ? speaker : indentation;
            stringBuilder.append(prefix).append(messages.get(i)).append("\n");
        }
        System.out.println(stringBuilder);
    }

    /**
     * get Wee with default setting
     * @return the default Wee
     */
    private static Wee getDefaultWee() {
        ResponsorRegistry responsorRegistry = new ResponsorRegistry();
        responsorRegistry.register(List.of("new", "hi"), new StartChatResponsor());
        responsorRegistry.register(List.of("exit", "bye"), new ExitChatResponsor());
        return new Wee(responsorRegistry,  new ChatData());
    }
}
