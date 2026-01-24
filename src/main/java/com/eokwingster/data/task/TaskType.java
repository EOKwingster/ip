package com.eokwingster.data.task;

public enum TaskType {
    TO_DO {
        @Override
        public Task createTask(String argument) {
            return new ToDo(argument);
        }
    },
    DEADLINE {
        @Override
        public Task createTask(String argument) {
            return new Deadline(argument, null);
        }
    },
    EVENT {
        @Override
        public Task createTask(String argument) {
            return new Event(argument, null, null);
        }
    };

    public abstract Task createTask(String argument);
}
