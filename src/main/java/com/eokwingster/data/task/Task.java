package com.eokwingster.data.task;

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

    protected abstract TaskType type();

    @Override
    public String toString() {
        String doneStatus = isDone ? "X" : " ";
        return String.format("[%s][%s] %s", type().toString().charAt(0), doneStatus, description);
    }
}
