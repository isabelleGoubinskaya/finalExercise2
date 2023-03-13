import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

class DailyTaskManager {
    private Scanner scanner;
    private TaskManager taskManager;

    public DailyTaskManager() {
        this.scanner = new Scanner(System.in);
        this.taskManager = new TaskManager();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a task");
            System.out.println("2. Remove a task");
            System.out.println("3. View tasks for today");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    viewTasksForToday();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addTask() {
        System.out.println("Please enter the task details:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Type (personal or work): ");
        String typeString = scanner.nextLine();
        TaskType type = TaskType.valueOf(typeString.toUpperCase());

        System.out.println("Please select a recurrence option:");
        System.out.println("1. One-time");
        System.out.println("2. Daily");
        System.out.println("3. Weekly");
        System.out.println("4. Monthly");
        System.out.println("5. Annual");

        int recurrenceChoice = scanner.nextInt();
        scanner.nextLine();
        RecurrenceOption recurrence;
        switch (recurrenceChoice) {
            case 1:
                recurrence = RecurrenceOption.ONE_TIME;
                break;
            case 2:
                recurrence = RecurrenceOption.DAILY;
                break;
            case 3:
                recurrence = RecurrenceOption.WEEKLY;
                break;
            case 4:
                recurrence = RecurrenceOption.MONTHLY;
                break;
            case 5:
                recurrence = RecurrenceOption.ANNUAL;
                break;
            default:
                System.out.println("Invalid choice. Task not added.");
                return;
        }

        LocalDateTime creationDateTime = LocalDateTime.now();
        int id = taskManager.generateTaskId();
        Task task = new Task(id, title, description, type, recurrence, creationDateTime);
        taskManager.addTask(task);

        System.out.println("Task added successfully.");
    }

    private void removeTask() {
        System.out.print("Please enter the ID of the task you want to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (taskManager.removeTaskById(id)) {
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void viewTasksForToday() {
        LocalDate today = LocalDate.now();
        List<Task> tasks = taskManager.getTasksForDate(today);

        System.out.println("Tasks for " + today + ":");
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}