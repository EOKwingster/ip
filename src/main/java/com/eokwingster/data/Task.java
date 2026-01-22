package com.eokwingster.data;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String doneStatus = isDone ? "X" : " ";
        return String.format("[%s] %s", doneStatus, description);
    }
}
