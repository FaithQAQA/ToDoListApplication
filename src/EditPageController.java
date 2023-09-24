
/**
*The EditPageController class is a controller responsible for handling user input for the edit page view of the to-do list. 
*It has methods for updating the selected task, completing a task, and removing a task from the list. 
*The class also has a List of ToDoTasks and a variable for storing the index of the selected task. 
*The selected task is updated when the user enters new data and clicks the save button. 
*If the user marks a task as completed, the class displays a confirmation dialog to ask if the user wants to remove the task
*from the list. If the user chooses to remove the task, the class removes the task from
*the list and adds the completed task to a text file.
*The class uses the List of ToDoTasks to update the selected task and remove
*tasks from the list. The ToDoList is saved to a file using a file named task and the FileActions interface, 
*which requires the implementation of a save method for saving the ToDoList to a file.
*@author Jalani
*@author Che
*/

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// EditPageController class handles the functionality of the edit task window GUI.
public class EditPageController implements Initializable {

    @FXML
    private TextField Task; // The description of the task to be edited.

    @FXML
    private Button close; // The button to close the GUI without saving any changes.

    @FXML
    private TextField dueDate; // The due date of the task to be edited.

    @FXML
    private ComboBox<String> priorityComboBox; // The priority level of the task to be edited.

    @FXML
    private Button remove; // The button to remove the task from the list.

    @FXML
    private Button saveButton; // The button to save the changes made to the task.

    private List<ToDoTask> taskList; // The list of tasks to be edited.

    // Sets the taskList field with the list of tasks to be edited.

    public void setTaskList(List<ToDoTask> taskList) {
        this.taskList = taskList;
    }

    int selectedIndex; // The index of the task selected to be edited

    /**
     * Sets the data of the task to the selected task data by default
     * 
     * @param index the index of the task to be edited
     * @param task  the task to be edited
     */
    public void setData(int index, ToDoTask task) {
        // Sets the field to what the user selected from the user view
        selectedIndex = index;
        Task.setText(task.getDescription());
        dueDate.setText(task.getDueDate());
        priorityComboBox.setValue(task.getPriority());
    }

    // This method handles the saving of changes made to the task
    @FXML
    void handleSaveButton(ActionEvent event) {

        String task = Task.getText();
        String dueDate1 = dueDate.getText();
        String priority = priorityComboBox.getValue();

        // Update the selected task
        taskList.get(selectedIndex).edit(task, dueDate1, priority);

        // Clear the fields
        Task.clear();
        dueDate.clear();
        priorityComboBox.getSelectionModel().clearSelection();

        // Close the window
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    // This method handles the remove task button to remove the selected task from the list
    @FXML
    void handleRemoveButton(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setHeaderText("Are you sure you want to remove this task?");
        alert.setContentText("Click OK to remove the task.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            taskList.remove(selectedIndex);
            // Close the window after removing or displaying the error message
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create the list of priorities for the priorityComboBox
        ObservableList<String> priorities = FXCollections.observableArrayList(
                "High",
                "Medium",
                "Low");
        // Set the items in the priorities to the priorityComboBox
        priorityComboBox.setItems(priorities);
    }
}
