package com.eokwingster;

import com.eokwingster.command.CommandRegistry;
import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.command.Step;
import com.eokwingster.command.keyword.Keywords;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.TaskType;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.responsors.ExitChatResponsor;
import com.eokwingster.responsor.responsors.StartChatResponsor;
import com.eokwingster.responsor.responsors.TaskAddResponsor;
import com.eokwingster.responsor.responsors.TaskBeginTimeResponsor;
import com.eokwingster.responsor.responsors.TaskDeleteResponsor;
import com.eokwingster.responsor.responsors.TaskDoneStatusResponsor;
import com.eokwingster.responsor.responsors.TaskEndTimeResponsor;
import com.eokwingster.responsor.responsors.TaskListResponsor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wee {
    public static final String NAME = "Wee";
    private final ChatData chatData;

    private Wee() {
        this.chatData = new ChatData();
    }

    public static void main(String[] args) {
        String logo = """
                █   █  █████  █████
                █   █  █      █
                █ █ █  ████   ████
                ██ ██  █      █
                █   █  █████  █████
                """;
        System.out.println(logo);

        Wee wee = new Wee();
        wee.setUp();
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
        List<Step> steps;
        Response.Builder response = Response.builder();
        try {
            steps = getStepsFromInput(input);
        } catch (IllegalArgumentException e) {
            return response.appendWarning(e.getMessage()).build();
        }
        for (Step step : steps) {
            CommandRegistry.getResponsor(step.keyword()).response(step.argument(), chatData, response, steps).withNextCommandN();
        }
        return response.build();
    }

    /**
     * Convert the user input into list of Step after validation
     * @param input the original user input
     * @return a list of Step objects
     * @see Step
     */
    public List<Step> getStepsFromInput(String input) throws IllegalArgumentException {
        List<String> stringSteps = List.of(input.split(" /"));
        List<Step> steps = new ArrayList<>();
        Keyword rootKeyword = null;
        for (int i = 0; i < stringSteps.size(); i++) {
            String[] step = stringSteps.get(i).split(" ", 2);
            String alias = step[0];
            String argument = step.length > 1 ? step[1] : "";

            //validation
            if (alias.isBlank()) {
                throw new IllegalArgumentException("Did you forget to input any keyword?");
            }
            Keyword keyword = CommandRegistry.getKeyword(alias);
            if (keyword == null) {
                throw new IllegalArgumentException(alias + "is not a keyword");
            }
            List<Keyword> rootKeywords = keyword.getRootKeywords();
            if (i == 0) {
                if (!rootKeywords.isEmpty()) {
                    throw new IllegalArgumentException("The first keyword must not be a modifier: " + alias);
                }
                rootKeyword = keyword;
            } else {
                if (rootKeywords.isEmpty()) {
                    throw new IllegalArgumentException("This keyword is not a modifier: " + alias);
                } else if (!rootKeywords.contains(rootKeyword)) {
                    throw new IllegalArgumentException(alias + " must follows a root keyword in: " + rootKeywords);
                }
            }
            keyword.validateStep(alias, argument, chatData);

            steps.add(new Step(keyword, alias, argument));
        }
        return steps;
    }

    /**
     * Run the necessary set up before the chat starts
     */
    private void setUp() {
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
    }
}
