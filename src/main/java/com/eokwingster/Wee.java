package com.eokwingster;

import com.eokwingster.command.CommandRegistry;
import com.eokwingster.command.keyword.AddDeadlineTaskKeyword;
import com.eokwingster.command.keyword.AddEventTaskKeyword;
import com.eokwingster.command.keyword.AddTodoTaskKeyword;
import com.eokwingster.command.keyword.DeleteTaskKeyword;
import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.command.Step;
import com.eokwingster.command.keyword.ExitChatKeyword;
import com.eokwingster.command.keyword.ListTasksKeyword;
import com.eokwingster.command.keyword.MarkTaskKeyword;
import com.eokwingster.command.keyword.NewChatKeyword;
import com.eokwingster.command.keyword.SetTaskBeginTimeKeyword;
import com.eokwingster.command.keyword.SetTaskEndTimeKeyword;
import com.eokwingster.command.keyword.UnmarkTaskKeyword;
import com.eokwingster.data.ChatData;
import com.eokwingster.data.task.TaskType;
import com.eokwingster.responsor.Modifier;
import com.eokwingster.responsor.Response;
import com.eokwingster.responsor.responsors.ExitChatResponsor;
import com.eokwingster.responsor.responsors.StartChatResponsor;
import com.eokwingster.responsor.responsors.TaskAddResponsor;
import com.eokwingster.responsor.responsors.TaskBeginTimeResponsor;
import com.eokwingster.responsor.responsors.TaskDeleteResponsor;
import com.eokwingster.responsor.responsors.TaskDoneStatusResponsor;
import com.eokwingster.responsor.responsors.TaskEndTimeResponsor;
import com.eokwingster.responsor.responsors.TaskListResponsor;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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
        String[] steps = input.split(" /");
        return IntStream.range(0, steps.length)
                .boxed()
                .map(i -> {
                    String[] step = steps[i].split(" ", 2);
                    String alias = step[0];
                    String argument = step.length > 1 ? step[1] : "";

                    //validation
                    Keyword keyword = CommandRegistry.getKeyword(alias);
                    if (keyword == null) {
                        throw new IllegalArgumentException(alias + "is not a keyword!");
                    } else if (i != 0 && !(CommandRegistry.getResponsor(keyword) instanceof Modifier)) {
                        throw new IllegalArgumentException("This command line has second root command: " + alias);
                    }
                    keyword.validateCommandStep(alias, argument, chatData);

                    return new Step(keyword, alias, argument);
                }).toList();
    }

    /**
     * Run the necessary set up before the chat starts
     */
    private void setUp() {
        CommandRegistry.registerResponsor(new StartChatResponsor(), new NewChatKeyword());
        CommandRegistry.registerResponsor(new ExitChatResponsor(), new ExitChatKeyword());
        CommandRegistry.registerResponsor(new TaskAddResponsor(TaskType.TO_DO), new AddTodoTaskKeyword());
        CommandRegistry.registerResponsor(new TaskAddResponsor(TaskType.DEADLINE), new AddDeadlineTaskKeyword());
        CommandRegistry.registerResponsor(new TaskAddResponsor(TaskType.EVENT), new AddEventTaskKeyword());
        CommandRegistry.registerResponsor(new TaskListResponsor(), new ListTasksKeyword());
        CommandRegistry.registerResponsor(new TaskDoneStatusResponsor(true), new MarkTaskKeyword());
        CommandRegistry.registerResponsor(new TaskDoneStatusResponsor(false), new UnmarkTaskKeyword());
        CommandRegistry.registerResponsor(new TaskBeginTimeResponsor(), new SetTaskBeginTimeKeyword());
        CommandRegistry.registerResponsor(new TaskEndTimeResponsor(), new SetTaskEndTimeKeyword());
        CommandRegistry.registerResponsor(new TaskDeleteResponsor(), new DeleteTaskKeyword());
    }
}
