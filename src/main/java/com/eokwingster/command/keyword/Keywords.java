package com.eokwingster.command.keyword;

import com.eokwingster.command.keyword.keywords.AddDeadlineTaskKeyword;
import com.eokwingster.command.keyword.keywords.AddEventTaskKeyword;
import com.eokwingster.command.keyword.keywords.AddTodoTaskKeyword;
import com.eokwingster.command.keyword.keywords.ClearTaskKeyword;
import com.eokwingster.command.keyword.keywords.DeleteTaskKeyword;
import com.eokwingster.command.keyword.keywords.ExitChatKeyword;
import com.eokwingster.command.keyword.keywords.FindTasksKeyword;
import com.eokwingster.command.keyword.keywords.ListTasksKeyword;
import com.eokwingster.command.keyword.keywords.MarkTaskKeyword;
import com.eokwingster.command.keyword.keywords.SetTaskBeginTimeKeyword;
import com.eokwingster.command.keyword.keywords.SetTaskEndTimeKeyword;
import com.eokwingster.command.keyword.keywords.StartChatKeyword;
import com.eokwingster.command.keyword.keywords.UnmarkTaskKeyword;

/**
 * All single instances of keywords are stored in this class as constants.
 */
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
    public static final Keyword CLEAR_TASK = new ClearTaskKeyword();
    public static final Keyword FIND_TASKS = new FindTasksKeyword();
}
