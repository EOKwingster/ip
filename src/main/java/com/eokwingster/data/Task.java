package com.eokwingster.data;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String doneStatus = isDone ? "X" : " ";
        return String.format("[%s] %s", doneStatus, description);
    }
}
