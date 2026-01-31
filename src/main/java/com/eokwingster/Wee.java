package com.eokwingster;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import com.eokwingster.client.UI;
import com.eokwingster.command.CommandParser;
import com.eokwingster.command.CommandRegistry;
import com.eokwingster.command.Step;
import com.eokwingster.command.keyword.Keywords;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.Storage;
import com.eokwingster.data.task.TaskType;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.responsors.ExitChatResponsor;
import com.eokwingster.responsor.responsors.StartChatResponsor;
import com.eokwingster.responsor.responsors.TaskAddResponsor;
import com.eokwingster.responsor.responsors.TaskBeginTimeResponsor;
import com.eokwingster.responsor.responsors.TaskClearResponsor;
import com.eokwingster.responsor.responsors.TaskDeleteResponsor;
import com.eokwingster.responsor.responsors.TaskDoneStatusResponsor;
import com.eokwingster.responsor.responsors.TaskEndTimeResponsor;
import com.eokwingster.responsor.responsors.TaskListResponsor;

/**
 * Main class of the chatbot, handles the setup and lifecycle
 */
public class Wee {
    public static final String NAME = "Wee";
    private final Scanner scanner;
    private final ChatData chatData;
    private final Storage storage;
    private final UI ui;

    private Wee() {
        scanner = new Scanner(System.in);
        chatData = new ChatData();
        storage = new Storage();
        ui = new UI();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Wee wee = new Wee();
        wee.setUp();
        wee.run();
    }

    private void run() throws IOException, URISyntaxException {
        printLogo();
        ui.display(getResponseFromInput("new"));
        while (true) {
            String input = scanner.nextLine();
            Response response = getResponseFromInput(input);
            ui.display(response);
            if (response.tags().contains(Response.Tag.SAVE)) {
                storage.save(chatData);
            }
            if (response.tags().contains(Response.Tag.EXIT)) {
                break;
            }
        }
    }

    private void printLogo() {
        String logo = """
                █   █  █████  █████
                █   █  █      █
                █ █ █  ████   ████
                ██ ██  █      █
                █   █  █████  █████
                """;
        System.out.println(logo);
    }

    /**
     * @param input user input
     * @return Response of this input
     */
    private Response getResponseFromInput(String input) {
        List<Step> steps;
        Response.Builder response = Response.builder();
        try {
            steps = CommandParser.getStepsFromInput(input, chatData);
        } catch (IllegalArgumentException e) {
            return response.appendWarning(e.getMessage()).build();
        }
        for (Step step : steps) {
            CommandRegistry.getResponsor(step.keyword())
                    .response(step.argument(), chatData, response, steps)
                    .withNextStepN();
        }
        return response.build();
    }

    /**
     * Run the necessary set up before the chat starts
     */
    private void setUp() throws IOException, URISyntaxException {
        register();
        storage.load(chatData);
    }

    private void register() {
        CommandRegistry.registerResponsor(new StartChatResponsor(), Keywords.START_CHAT);
        CommandRegistry.registerResponsor(new ExitChatResponsor(), Keywords.EXIT_CHAT);
        CommandRegistry.registerResponsor(new TaskAddResponsor(TaskType.TO_DO), Keywords.ADD_TODO_TASK);
        CommandRegistry.registerResponsor(new TaskAddResponsor(TaskType.DEADLINE), Keywords.ADD_DEADLINE_TASK);
        CommandRegistry.registerResponsor(new TaskAddResponsor(TaskType.EVENT), Keywords.ADD_EVENT_TASK);
        CommandRegistry.registerResponsor(new TaskListResponsor(), Keywords.LIST_TASK);
        CommandRegistry.registerResponsor(new TaskDoneStatusResponsor(true), Keywords.MARK_TASK);
        CommandRegistry.registerResponsor(new TaskDoneStatusResponsor(false), Keywords.UNMARK_TASK);
        CommandRegistry.registerResponsor(new TaskBeginTimeResponsor(), Keywords.SET_TASK_BEGIN);
        CommandRegistry.registerResponsor(new TaskEndTimeResponsor(), Keywords.SET_TASK_END);
        CommandRegistry.registerResponsor(new TaskDeleteResponsor(), Keywords.DELETE_TASK);
        CommandRegistry.registerResponsor(new TaskClearResponsor(), Keywords.CLEAR_TASK);
    }
}
