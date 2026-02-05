package com.eokwingster.data.task;

import com.eokwingster.util.Utils;

/**
 * Basic task structure and methods, contains a string description and isDone boolean status
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * If a string is equal to description.
     * @param s a string
     * @return if equal
     */
    public boolean ifDescriptionMatch(String s) {
        return description.equals(s);
    }

    /**
     * If a string is contained the description.
     * @param s a string
     * @return if contained
     */
    public boolean ifDescriptionMatchPartially(String s) {
        return description.contains(s);
    }

    /**
     * If a string is similar with the description, base on the N-Grams Dice factor algorithm.
     * @param s a string
     * @param targetSimilarity the similarity lower bound to return true
     * @return if similar
     * @see Utils#getNGramDiceOfTwoStrings(String, String)
     */
    public boolean ifDescriptionMatchSimilarly(String s, float targetSimilarity) {
        assert targetSimilarity >= 0 && targetSimilarity <= 1 : "a percentage must be between 0 and 1";
        float dice = Utils.getNGramDiceOfTwoStrings(description, s);
        return dice >= targetSimilarity;
    }

    /**
     * Get type in TaskType enum of this task class
     * @return A TaskType
     */
    public abstract TaskType getType();

    @Override
    public String toString() {
        String doneStatus = isDone ? "X" : " ";
        return String.format("[%s][%s] %s", getType().toString().charAt(0), doneStatus, description);
    }
}
