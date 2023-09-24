
/**
*The ToDoList class represents a list of ToDoTasks.
*It has methods for adding tasks to the list, as well as getters and setters.
*This class uses an ArrayList to store the tasks 
*The ToDoList class uses the ToDoTask class to represent individual tasks.
@author Jalani
@author Che
*/

import java.util.ArrayList;
import java.util.List;

// A class representing a to-do list, which contains a list of tasks.
public class ToDoList {

    private List<ToDoTask> tasks;

    // Constructs a new empty to-do list.
    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    // Adds a new task to the list.
    public void addTask(ToDoTask task) {
        tasks.add(task);
    }

    // Returns a list of all the tasks in the to-do list.
    public List<ToDoTask> getTasks() {
        return tasks;
    }

    // Sets the tasks in the to-do list to the given list of tasks.
    public void setTasks(List<ToDoTask> tasks) {
        this.tasks = tasks;
    }
}
