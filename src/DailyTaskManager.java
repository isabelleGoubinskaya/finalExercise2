import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public List<Task> getTasksForDate(LocalDate date) {
        List<Task> tasksForDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isRepeat() || date.equals(LocalDate.now())) {
                tasksForDate.add(task);
            }
        }
        return tasksForDate;
    }

    public void removeTask() {
    }

    public int generateTaskId() {
        return 0;
    }

    public boolean removeTaskById(int id) {
        return false;
    }
}