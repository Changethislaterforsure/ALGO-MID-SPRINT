package algo.midsprint;

public class TaskList {
    
    private static class Node {
        Task task;
        Node next;
        Node(Task task) { this.task = task; }
    }

    private Node head;
    private Node tail;
    private int size;

    
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

   
    public boolean markTaskCompleted(int index) {
        if (index < 0 || index >= size) return false;
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        curr.task.markCompleted();
        return true;
    }

   
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