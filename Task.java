package mininotion;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 1;
    private final int id;
    private String title, description, dueDate, priority;
    private boolean isCompleted;

    public Task(String title, String description, String dueDate, String priority) {
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDueDate() { return dueDate; }
    public String getPriority() { return priority; }
    public void complete() { this.isCompleted = true; }
    public boolean isCompleted() { return isCompleted; }

    @Override
    public String toString() {
        return "Task ID: " + id + "\nTitle: " + title + "\nDescription: " + description +
                "\nDue Date: " + dueDate + "\nPriority: " + priority +
                "\nCompleted: " + (isCompleted ? "Yes" : "No") + "\n----------------------------";
    }
}
