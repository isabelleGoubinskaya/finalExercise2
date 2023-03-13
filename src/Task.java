import java.time.LocalDateTime;

class Task {
    private String title;
    private String description;
    private TaskType type;
    private boolean repeat;

    public Task(String title, String description, TaskType type, boolean repeat) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.repeat = repeat;
    }

    public Task(int id, String title, String description, TaskType type, RecurrenceOption recurrence, LocalDateTime creationDateTime) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nDescription: " + description +
                "\nType: " + type +
                "\nRepeat: " + repeat;
    }
}