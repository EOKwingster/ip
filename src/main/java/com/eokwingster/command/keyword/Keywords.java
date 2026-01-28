package com.eokwingster.command.keyword;

public class Keywords {
    public static final Keyword START_CHAT = new StartChatKeyword();
    public static final Keyword EXIT_CHAT = new ExitChatKeyword();
    public static final Keyword ADD_TODO_TASK = new AddTodoTaskKeyword();
    public static final Keyword ADD_DEADLINE_TASK = new AddDeadlineTaskKeyword();
    public static final Keyword ADD_EVENT_TASK = new AddEventTaskKeyword();
    public static final Keyword LIST_TASK = new ListTasksKeyword();
    public static final Keyword MARK_TASK = new MarkTaskKeyword();
    public static final Keyword UNMARK_TASK = new UnmarkTaskKeyword();
    public static final Keyword SET_TASK_BEGIN = new SetTaskBeginTimeKeyword();
    public static final Keyword SET_TASK_END = new SetTaskEndTimeKeyword();
    public static final Keyword DELETE_TASK = new DeleteTaskKeyword();
}
