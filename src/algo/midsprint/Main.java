package algo.midsprint;

import java.util.Scanner;

public class Main {

    private static final int MAX_USERS = 10;
    private static final User[] USERS = new User[MAX_USERS];

    public static int addUser(User[] users, String name) {
        int freeSlot = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null && freeSlot == -1) freeSlot = i;
            if (users[i] != null && users[i].getName().equalsIgnoreCase(name)) return -1;
        }
        if (freeSlot == -1) return -2;
        users[freeSlot] = new User(name);
        return freeSlot;
    }

    public static int findUser(User[] users, String name) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getName().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    private static void printMenu() {
        System.out.println("\n==== To-Do List Manager ====");
        System.out.println("1) Add user");
        System.out.println("2) List users");
        System.out.println("3) Add task to a user");
        System.out.println("4) Mark task completed (by index)");
        System.out.println("5) Mark task completed (by description)");
        System.out.println("6) View all tasks for a user");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
    }

    private static void listUsers() {
        System.out.println("Users:");
        boolean any = false;
        for (int i = 0; i < USERS.length; i++) {
            if (USERS[i] != null) {
                System.out.println("  - " + USERS[i].getName());
                any = true;
            }
        }
        if (!any) System.out.println("  (no users yet)");
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                String choice = in.nextLine().trim();

                switch (choice) {
                    case "1": {
                        System.out.print("Enter new user's unique name: ");
                        String name = in.nextLine().trim();
                        int code = addUser(USERS, name);
                        if (code == -1) {
                            System.out.println("A user with that name already exists.");
                        } else if (code == -2) {
                            System.out.println("User list is full.");
                        } else {
                            System.out.println("Added user '" + name + "' at index " + code + ".");
                        }
                        break;
                    }
                    case "2": {
                        listUsers();
                        break;
                    }
                    case "3": {
                        System.out.print("User name: ");
                        String name = in.nextLine().trim();
                        int idx = findUser(USERS, name);
                        if (idx == -1) {
                            System.out.println("User not found.");
                            break;
                        }
                        System.out.print("Task description: ");
                        String desc = in.nextLine();
                        try {
                            USERS[idx].addTask(desc);
                            System.out.println("Task added to " + name + ".");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case "4": {
                        System.out.print("User name: ");
                        String name = in.nextLine().trim();
                        int idx = findUser(USERS, name);
                        if (idx == -1) {
                            System.out.println("User not found.");
                            break;
                        }
                        System.out.print("Task index (0-based): ");
                        String s = in.nextLine().trim();
                        try {
                            int tIdx = Integer.parseInt(s);
                            boolean ok = USERS[idx].markTaskCompleted(tIdx);
                            System.out.println(ok ? "Marked complete." : "Index out of range.");
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer index.");
                        }
                        break;
                    }
                    case "5": {
                        System.out.print("User name: ");
                        String name = in.nextLine().trim();
                        int idx = findUser(USERS, name);
                        if (idx == -1) {
                            System.out.println("User not found.");
                            break;
                        }
                        System.out.print("Exact task description: ");
                        String desc = in.nextLine();
                        boolean ok = USERS[idx].markTaskCompleted(desc);
                        System.out.println(ok ? "Marked complete." : "No matching task found.");
                        break;
                    }
                    case "6": {
                        System.out.print("User name: ");
                        String name = in.nextLine().trim();
                        int idx = findUser(USERS, name);
                        if (idx == -1) {
                            System.out.println("User not found.");
                            break;
                        }
                        USERS[idx].printAllTasks();
                        break;
                    }
                    case "0": {
                        running = false;
                        System.out.println("Bye!");
                        break;
                    }
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
}