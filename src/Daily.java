import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DailyTaskManager {
    private Scanner scanner;
    private List<Task> tasks;
    private int taskIdCounter;

    public DailyTaskManager() {
        this.scanner = new Scanner(System.in);
        this.tasks = new ArrayList<>();
        this.taskIdCounter = 1;
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
        Recurrence recurrence;
        switch (recurrenceChoice) {
            case 1:
                recurrence = Recurrence.ONE_TIME;
                break;
            case 2:
                recurrence = Recurrence.DAILY;
                break;
            case 3:
                recurrence = Recurrence.WEEKLY;
                break;
            case 4:
                recurrence = Recurrence.MONTHLY;
                break;
            case 5:
                recurrence = Recurrence.ANNUAL;
                break;
            default:
                System.out.println("Invalid choice. Task not added.");
                return;
        }

        LocalDateTime creationDateTime = LocalDateTime.now();
        int id = generateTaskId();
        Task task = new Task(id, title, description, type, recurrence, creationDateTime);
        tasks.add(task);

        System.out.println("Task added successfully.");
    }

    private void removeTask() {
        System.out.print("Please enter the ID of the task you want to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = false;
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void viewTasksForToday() {
        LocalDate today = LocalDate.now();
        List<Task> tasksForToday = getTasksForDate(today);

        System.out.println("Tasks for " + today + ":");
        if (tasksForToday.isEmpty()) {
            System.out.println("No tasks.");
        } else {
            for (Task task : tasksForToday) {
                System.out.println(task);
            }
        }
    }

    private List<Task> getTasksForDate(LocalDate date) {
        List<Task> tasksForDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getRecurrence() == Recurrence.ONE_TIME && task.getCreationDateTime().toLocalDate().equals(date)) {
                tasksForDate.add(task);
            } else if (task.getRecurrence() != Recurrence.ONE_TIME && isMatchingRecurrence(task, date)) {
                tasksForDate.add(task);
            }
        }
        return tasksForDate;
    }

    private boolean isMatchingRecurrence(Task task, LocalDate date) {
        switch (task.getRecurrence()) {
            case DAILY:
                return true;
            case WEEKLY:
                return task.getCreationDateTime().getDayOfWeek() == date.getDayOfWeek();
            case MONTHLY:
                return task.getCreationDateTime().getDayOfMonth() == date.getDayOfMonth();
            case ANNUAL:
                return task.getCreationDateTime().getMonth() == date.getMonth()
                        && task.getCreationDateTime().getDayOfMonth() == date.getDayOfMonth();
            default:
                return false;
        }
    }

    private int generateTaskId() {
        return taskIdCounter++;
    }

    private enum Recurrence {
        ONE_TIME,
        DAILY,
        WEEKLY,
        MONTHLY,
        ANNUAL
    }

    private static class Task {
        private int id;
        private String title;
        private String description;
        private TaskType type;
        private Recurrence recurrence;
        private LocalDateTime creationDateTime;

        public Task(int id, String title, String description, TaskType type, Recurrence recurrence, LocalDateTime creationDateTime) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.type = type;
            this.recurrence = recurrence;
            this.creationDateTime = creationDateTime;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public TaskType getType() {
            return type;
        }

        public Recurrence getRecurrence() {
            return recurrence;
        }

        public LocalDateTime getCreationDateTime() {
            return creationDateTime;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", type=" + type +
                    ", recurrence=" + recurrence +
                    ", creationDateTime=" + creationDateTime +
                    '}';
        }
    }

    private enum TaskType {
        PERSONAL,
        WORK
    }
}

