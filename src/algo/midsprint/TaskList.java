/**
 * Singly-linked list of Task objects.
 */
public class TaskList {
    /** Node for singly-linked list. */
    private static class Node {
        Task task;
        Node next;
        Node(Task task) { this.task = task; }
    }

    private Node head;
    private Node tail;
    private int size;

    /** Add a new task with the given description to the end of the list. */
    public void addTask(String description) {
        Task task = new Task(description);
        Node n = new Node(task);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    /**
     * Mark a task as completed by zero-based index.
     * @return true if successful, false if index out of range
     */
    public boolean markTaskCompleted(int index) {
        if (index < 0 || index >= size) return false;
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        curr.task.markCompleted();
        return true;
    }

    /**
     * Mark the first task whose description equalsIgnoreCase(desc) as completed.
     * @return true if a matching task was found and marked
     */
    public boolean markTaskCompleted(String desc) {
        if (desc == null) return false;
        Node curr = head;
        while (curr != null) {
            if (curr.task.getDescription().equalsIgnoreCase(desc)) {
                curr.task.markCompleted();
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /** Print all tasks with their indices and completion status. */
    public void printAllTasks() {
        if (head == null) {
            System.out.println("  (no tasks)");
            return;
        }
        Node curr = head;
        int i = 0;
        while (curr != null) {
            System.out.println("  " + i + ". " + curr.task);
            curr = curr.next;
            i++;
        }
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}