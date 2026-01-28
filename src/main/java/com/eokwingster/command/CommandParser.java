package com.eokwingster.command;

import com.eokwingster.command.keyword.CommandRoot;
import com.eokwingster.command.keyword.Keyword;
import com.eokwingster.data.ChatData;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    /**
     * Convert the user input into list of Step after validation
     * @param input the original user input
     * @return a list of Step objects
     * @see Step
     */
    public static List<Step> getStepsFromInput(String input, ChatData chatData) throws IllegalArgumentException {
        List<String> stringSteps = List.of(input.split(" /"));
        List<Step> steps = new ArrayList<>();
        String rootAlias = null;
        List<Keyword> requiredModifiers = new ArrayList<>();
        for (int i = 0; i < stringSteps.size(); i++) {
            String[] step = stringSteps.get(i).split(" ", 2);
            String alias = step[0];
            String argument = step.length > 1 ? step[1] : "";
            Keyword keyword = CommandRegistry.getKeyword(alias);
            //validation
            validateKeyword(alias, keyword);
            if (i == 0) {
                validateRoot(alias, keyword);
                rootAlias = alias;
                requiredModifiers.addAll(((CommandRoot) keyword).getRequiredModifiers());
            } else {
                validateModifier(alias, keyword, rootAlias, requiredModifiers);
            }
            keyword.validateStep(alias, argument, chatData);

            steps.add(new Step(keyword, alias, argument));
        }
        finalValidate(requiredModifiers);
        return steps;
    }

    private static void validateKeyword(String alias, Keyword keyword) throws IllegalArgumentException {
        if (alias.isBlank()) {
            throw new IllegalArgumentException("Did you forget to input any keyword?");
        }
        if (keyword == null) {
            throw new IllegalArgumentException(alias + " is not a keyword");
        }
    }

    private static void validateRoot(String alias, Keyword keyword) throws IllegalArgumentException {
        if (!(keyword instanceof CommandRoot)) {
            throw new IllegalArgumentException("The first keyword must not be a modifier: " + alias);
        }
    }

    private static void validateModifier(String alias, Keyword keyword, String rootAlias, List<Keyword> requiredModifiers) throws IllegalArgumentException {
        if (keyword instanceof CommandRoot) {
            throw new IllegalArgumentException("This keyword is not a modifier: " + alias);
        } else if (!requiredModifiers.remove(keyword)) {
            throw new IllegalArgumentException(alias + " is not a legal modifier for " + rootAlias);
        }
    }

    private static void finalValidate(List<Keyword> requiredModifiers) {
        if (!requiredModifiers.isEmpty()) {
            throw new IllegalArgumentException("Some required modifiers lost!");
        }
    }
}
