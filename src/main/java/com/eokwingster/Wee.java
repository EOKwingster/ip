package com.eokwingster;

import com.eokwingster.command.Step;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.TaskType;
import com.eokwingster.response.Response;
import com.eokwingster.responsor.*;
import com.eokwingster.responsor.responsors.*;

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
            if (response.tags().contains(Response.Tag.Exit)) {
                break;
            }
        }
    }

    /**
     * @param input user input
     * @return Response of this input
     */
    private Response getResponseFromInput(String input) {
        List<Step> steps = Step.listOf(input);
        Response.Builder response = Response.builder();
        Responsor responsor = new UnknownResponsor("");
        Responsor lastRootResponsor = null;
        for (Step step : steps) {
            if (response.hasTags(Response.Tag.Modifier)) {
                response.removeLastStepStartPoint();
                response.removeTags(Response.Tag.Modifier);
            }
            response.markStepStartPoint();
            if (!(responsor instanceof Modifier)) {
                lastRootResponsor = responsor;
            }
            responsor = responsorRegistry.getResponsor(step.keyword());
            if (responsor instanceof Modifier modifier) {
                if (!modifier.getRootResponsors().contains(lastRootResponsor.getClass())) {
                    String errorMessage = String.format("%s command must follow commands: %s", step.keyword(), modifier.getRootResponsors());
                    responsor = new ErrorResponsor(errorMessage);
                }
            }
            response = responsor.response(step.argument(), chatData, response, steps).withNextCommandN();
            if (response.hasTags(Response.Tag.Final)) {
                break;
            }
        }
        return response.build();
    }

    /**
     * get Wee with default setting
     * @return the default Wee
     */
    private static Wee getDefaultWee() {
        ResponsorRegistry responsorRegistry = new ResponsorRegistry();
        responsorRegistry.register(List.of("new", "hi"), new StartChatResponsor());
        responsorRegistry.register(List.of("exit", "bye"), new ExitChatResponsor());
        responsorRegistry.register("todo", new TaskAddResponsor(TaskType.ToDo));
        responsorRegistry.register("deadline", new TaskAddResponsor(TaskType.Deadline));
        responsorRegistry.register("event", new TaskAddResponsor(TaskType.Event));
        responsorRegistry.register("list", new TaskListResponsor());
        responsorRegistry.register("mark", new TaskDoneStatusResponsor(true));
        responsorRegistry.register("unmark", new TaskDoneStatusResponsor(false));
        return new Wee(responsorRegistry,  new ChatData());
    }
}
