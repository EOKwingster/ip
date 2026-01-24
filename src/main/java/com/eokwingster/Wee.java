package com.eokwingster;

import com.eokwingster.command.Keyword;
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
                █   █  █████  █████
                █   █  █      █
                █ █ █  ████   ████
                ██ ██  █      █
                █   █  █████  █████
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
        Responsor responsor;
        Keyword lastRootKeyword = null;
        for (Step step : steps) {
            // step label
            if (response.hasTags(Response.Tag.Modifier)) {
                response.removeLastStepStartPoint();
                response.removeTags(Response.Tag.Modifier);
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
                responsor = new UnknownResponsor(step.keyword());
            } else {
                responsor = responsorRegistry.getResponsor(keyword);
            }

            // maintain modifier command structure
            if (responsor instanceof Modifier modifier) {
                if (!modifier.getRootKeywords().contains(lastRootKeyword)) {
                    String errorMessage = String.format("%s command must follow commands: %s", keyword, modifier.getRootKeywords());
                    responsor = new ErrorResponsor(errorMessage);
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
    private static Wee getDefaultWee() {
        ResponsorRegistry responsorRegistry = new ResponsorRegistry();
        responsorRegistry.register(new StartChatResponsor(), Keyword.NEW, Keyword.HI);
        responsorRegistry.register(new ExitChatResponsor(), Keyword.EXIT, Keyword.BYE);
        responsorRegistry.register(new TaskAddResponsor(TaskType.TO_DO), Keyword.TODO);
        responsorRegistry.register(new TaskAddResponsor(TaskType.DEADLINE), Keyword.DEADLINE);
        responsorRegistry.register(new TaskAddResponsor(TaskType.EVENT), Keyword.EVENT);
        responsorRegistry.register(new TaskListResponsor(), Keyword.LIST);
        responsorRegistry.register(new TaskDoneStatusResponsor(true), Keyword.MARK);
        responsorRegistry.register(new TaskDoneStatusResponsor(false), Keyword.UNMARK);
        responsorRegistry.register(new TaskBeginTimeResponsor(), Keyword.FROM);
        responsorRegistry.register(new TaskEndTimeResponsor(), Keyword.BY, Keyword.TO);
        return new Wee(responsorRegistry,  new ChatData());
    }
}
