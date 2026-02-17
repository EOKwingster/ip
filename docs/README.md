# Wee User Guide

Wee is a multifunctional task management conversational robot with:
- Simple screen
- Ultra-low learning cost

## Command Structure

Simplest command is composed of a root alias and an optional argument.

Example: `bye`

Results:
```
Bye. Hope to see you again soon!
```

Example: `todo work`

Results:
```
Task added:
  [T][ ] work
Now you have 1 tasks.
```

Some root aliases can be followed by secondary modifier alias to form a more complex command.

Example: `deadline submit report /by 2026-06-06`

Results:
```
Task added:
  [D][ ] submit report (by: 2026 Jun 06 23:59 PM)
Now you have 2 tasks.
```

## Aliases List

| Root Alias     | Argument         | Secondary Modifier Alias | Description                  |
|----------------|------------------|-------------|------------------------------|
| `hi new start` | N/A              | N/A         | start new chat               |
| `bye exit end` | N/A              | N/A         | Exit the program             |
| `list`         | N/A              | N/A         | List all tasks               |
| `todo`         | task name        | N/A         | Add a todo task              |
| `deadline`     | task name        | `/by`       | Add a deadline task          |
| `event`        | task name        | `/from /to` | Add an event task            |
| `/by /from`    | YYYY-MM-DD HH:mm | N/A         | set a beginning time         |
| `/to`          | YYYY-MM-DD HH:mm | N/A         | set an ending time           |
| `mark`         | task number      | N/A         | Mark a task as done          |
| `unmark`       | task number      | N/A         | Mark a task as not done      |
| `delete`       | task number      | N/A         | Delete a task                |
| `find`         | keyword          | N/A         | Find tasks by keyword        |
| `clear`        | N/A              | N/A         | delete all tasks in the list |