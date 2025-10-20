package algo.midsprint;

public class Task {
    private final String description;
    private boolean completed;

    public Task(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be empty.");
        }
        this.description = description;
        this.completed = false;
    }

    
    public void markCompleted() {
        this.completed = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return (completed ? "[✓] " : "[ ] ") + description;
    }
}
