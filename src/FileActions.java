
/**
*The FileActions interface is used for saving the tasks in the list to a file.
*It requires the implementation of a save method that takes in a filename and a list of ToDoTasks to be saved to the file.
*The implementing class should define the details of how the ToDoList is saved to the file.
*@author Jalani
*@author che
*/

import java.util.List;

public interface FileActions {

    /**
     * Saves the given list of tasks to the specified file.
     * 
     * @param filename the name of the file to save the data to
     * @param taskList the list of to-do tasks to be saved
     */
    void save(String filename, List<ToDoTask> taskList);
}