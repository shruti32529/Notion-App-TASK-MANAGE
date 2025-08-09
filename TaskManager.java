package mininotion;

import java.util.*;

public class TaskManager {
    private List<Task> tasks;
    private Stack<List<Task>> undoStack;
    private int tasksCreated = 0, tasksCompleted = 0;

    public TaskManager() {
        tasks = new ArrayList<>();
        undoStack = new Stack<>();
    }

    public void createTask(String title, String description, String dueDate, String priority) {
        saveStateForUndo();
        tasks.add(new Task(title, description, dueDate, priority));
        tasksCreated++;
        System.out.println("Task added successfully!");
    }

    public void displayTasks() {
        System.out.println("\n--- All Tasks (Sorted by Due Date) ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        List<Task> tasksByDueDate = new ArrayList<>(tasks);
        customSortByDueDate(tasksByDueDate);
        for (Task t : tasksByDueDate) {
            System.out.println(t);
        }

        System.out.println("\n--- High Priority Tasks ---");
        List<Task> tasksByPriority = new ArrayList<>(tasks);
        customSortByPriority(tasksByPriority);
        for (Task t : tasksByPriority) {
            System.out.println(t);
        }

        System.out.println("\n--- Completed Tasks ---");
        boolean found = false;
        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) System.out.println("No completed tasks found.");
    }

    public void markTaskAsCompleted(int taskId) {
        saveStateForUndo();
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.complete();
                tasksCompleted++;
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found with ID: " + taskId);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            tasks.clear();
            tasks.addAll(undoStack.pop());
            System.out.println("Undo successful!");
        } else {
            System.out.println("No actions to undo.");
        }
    }

    private void saveStateForUndo() {
        List<Task> copy = new ArrayList<>();
        for (Task t : tasks) {
            Task copyTask = new Task(t.getTitle(), "", t.getDueDate(), t.getPriority());
            if (t.isCompleted()) {
                copyTask.complete();
            }
            copy.add(copyTask);
        }
        undoStack.push(copy);
    }

    public void taskSummary() {
        System.out.println("Task Session Summary:");
        System.out.println("Tasks Created: " + tasksCreated + ", Completed: " + tasksCompleted +
                ", Pending: " + (tasksCreated - tasksCompleted));
    }

    private void customSortByDueDate(List<Task> list) {
        for (int i = 0; i < list.size() -1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).getDueDate().compareTo(list.get(j + 1).getDueDate()) > 0) {
                    Task temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private void customSortByPriority(List<Task> list) {
        for (int i = 0; i < list.size() -1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (priorityValue(list.get(j).getPriority()) > priorityValue(list.get(j + 1).getPriority())) {
                    Task temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private int priorityValue(String priority) {
        switch (priority.toLowerCase()) {
            case "high": return 1;
            case "medium": return 2;
            default: return 3;
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> loadedTasks) {
        tasks = loadedTasks;
    }
}
