package com.eokwingster;

import com.eokwingster.command.CommandRegistry;
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
import com.eokwingster.responsor.Responsor;
import com.eokwingster.responsor.ResponsorRegistry;
import com.eokwingster.responsor.responsors.ErrorResponsor;
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
        List<Step> steps = Step.listOf(input);
        Response.Builder response = Response.builder();
        Responsor responsor;
        Keyword lastRootKeyword = null;
        for (Step step : steps) {
            // step label
            if (response.hasTags(Response.Tag.Modifier)) {
                response.removeLastStepStartPoint().removeTags(Response.Tag.Modifier);
            }
            response.markStepStartPoint();

            // get Keyword from string
            Keyword keyword;
            try {
                keyword = Keyword.valueOf(step.keyword().toUpperCase());
            } catch (NullPointerException | IllegalArgumentException e) {
                keyword = Keyword.UNKNOWN;
            }

            // record root keyword if the last keyword is, and get new responsor
            if (!keyword.isModifier()) {
                lastRootKeyword = keyword;
            }
            if (keyword == Keyword.UNKNOWN) {
                responsor = ErrorResponsor.of("I don't understand: " + step, "What are you talking about?");
            } else {
                responsor = responsorRegistry.getResponsor(keyword);
            }

            // maintain modifier command structure
            if (responsor instanceof Modifier modifier) {
                if (!modifier.getRootKeywords().contains(lastRootKeyword)) {
                    responsor = ErrorResponsor.of(String.format("%s command must follow commands: %s", keyword, modifier.getRootKeywords()));
                }
            }

            // get response
            response = responsor.response(step.argument(), chatData, response, steps).withNextCommandN();

            //terminate subsequent responses if final tag
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
    public List<Step> getStepsFromInput(String input) throws IllegalArgumentException {
        String[] steps = input.split(" /");
        return IntStream.range(0, steps.length)
                .boxed()
                .map(i -> {
                    String[] step = steps[i].split(" ", 2);
                    String alias = step[0];
                    String argument = step.length > 1 ? step[1] : "";
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
