package algo.midsprint;


public class User {
    private final String name;
    private final TaskList tasks = new TaskList();

    public User(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void addTask(String description) {
        tasks.addTask(description);
    }

    public boolean markTaskCompleted(int index) {
        return tasks.markTaskCompleted(index);
    }

    public boolean markTaskCompleted(String description) {
        return tasks.markTaskCompleted(description);
    }

    public void printAllTasks() {
        System.out.println("Tasks for " + name + ":");
        tasks.printAllTasks();
    }
}
