
/**
 * The ToDoTask class represents one task that will be in the to-do list.
 * It contains a description, due date, and priority fields, with methods for
 * editing the task and
 * formatting the text to populate the ListView
 * 
 * @author Jalani
 * @author Che
 */

public class ToDoTask {
    private String description; // The description of the task
    private String dueDate; // The due date of the task
    private String priority; // The priority of the task

    /**
     * Constructor to create a new ToDoTask object with the given description, due
     * date, and priority.
     */
    public ToDoTask(String description, String dueDate, String priority) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    // Get the description of the task.
    public String getDescription() {
        return description;
    }

    // Set the description of the task.
    public void setDescription(String description) {
        this.description = description;
    }

    // Get the due date of the task.
    public String getDueDate() {
        return dueDate;
    }

    // Set the due date of the task.
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // Get the priority of the task.
    public String getPriority() {
        return priority;
    }

    // Set the priority of the task.
    public void setPriority(String priority) {
        this.priority = priority;
    }

    // Edit the task by updating its description, due date, and priority.
    public void edit(String newTask, String newDueDate, String newPriority) {
        this.description = newTask;
        this.dueDate = newDueDate;
        this.priority = newPriority;
    }

    // Override the toString() method to provide a formatted string representation
    // of the task.
    @Override
    public String toString() {
        return String.format("%s, Due Date: %s, Priority: %s",
                description, dueDate, priority);
    }
}
