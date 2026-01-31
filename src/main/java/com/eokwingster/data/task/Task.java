package com.eokwingster.data.task;

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

    public boolean ifDescriptionContains(String s) {
        return description.contains(s);
    }

    /**
     * Get type in TaskType enum of this task class
     * @return A TaskType
     */
    public abstract TaskType type();

    @Override
    public String toString() {
        String doneStatus = isDone ? "X" : " ";
        return String.format("[%s][%s] %s", type().toString().charAt(0), doneStatus, description);
    }
}
