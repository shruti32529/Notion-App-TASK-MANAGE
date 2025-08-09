package mininotion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 1;
    private final int id;
    private String title, content;
    private long timestamp;
    private boolean isPinned;
    private Set<String> tags;
    private Stack<String> versionHistory;

    public Note(String title, String content, Set<String> tags) {
        this.id = counter++;
        this.title = title;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.isPinned = false;
        this.tags = tags != null ? tags : new HashSet<>();
        this.versionHistory = new Stack<>();
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public long getTimestamp() { return timestamp; }
    public boolean isPinned() { return isPinned; }
    public Set<String> getTags() { return tags; }

    public void setTitle(String title) {
        versionHistory.push(this.title + "::" + this.content);
        this.title = title; updateTimestamp();
    }

    public void setContent(String content) {
        versionHistory.push(this.title + "::" + this.content);
        this.content = content; updateTimestamp();
    }

    public void setPinned(boolean pinned) { this.isPinned = pinned; }
    public void updateTimestamp() { this.timestamp = System.currentTimeMillis(); }

    public void undo() {
        if (!versionHistory.isEmpty()) {
            String[] prev = versionHistory.pop().split("::");
            this.title = prev[0]; this.content = prev[1]; updateTimestamp();
        }
    }

    public void display() {
        System.out.println("[" + (isPinned ? "PINNED" : "NOTE") + "] ID: " + id);
        System.out.println("Title: " + title + "\nContent: " + content + "\nTags: " + tags);
        System.out.println("Created: " + new Date(timestamp) + "\n----------------------------");
    }
}
