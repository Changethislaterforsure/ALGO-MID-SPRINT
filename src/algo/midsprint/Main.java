package algo.midsprint;

public class Main {

   
    public static int addUser(User[] users, String name) {
        int freeSlot = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null && freeSlot == -1) freeSlot = i;
            if (users[i] != null && users[i].getName().equalsIgnoreCase(name)) return -1; // duplicate
        }
        if (freeSlot == -1) return -2; // array full
        users[freeSlot] = new User(name);
        return freeSlot;
    }

    
    public static int findUser(User[] users, String name) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getName().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        User[] users = new User[5];

        System.out.println("Creating users...");
        System.out.println("Add Alice -> index: " + addUser(users, "Alice"));
        System.out.println("Add Bob   -> index: " + addUser(users, "Bob"));
        System.out.println("Add Alice again (duplicate) -> index: " + addUser(users, "Alice")); // -1

        int aliceIdx = findUser(users, "Alice");
        int bobIdx   = findUser(users, "Bob");

        users[aliceIdx].addTask("Buy groceries");
        users[aliceIdx].addTask("Finish Java assignment");
        users[aliceIdx].addTask("Call mom");

        users[bobIdx].addTask("Book dentist appointment");
        users[bobIdx].addTask("Read 20 pages of a book");

        users[aliceIdx].markTaskCompleted(1); // Finish Java assignment
        users[bobIdx].markTaskCompleted("Read 20 pages of a book");

        System.out.println();
        users[aliceIdx].printAllTasks();
        System.out.println();
        users[bobIdx].printAllTasks();
    }
}