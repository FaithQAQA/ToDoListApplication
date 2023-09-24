
/**
 * The Controller class is for handling the events of the interface elements in the main GUI,
 * and handles user input for the ToDoList application.
 * There are methods for adding tasks to a list, opening a window to edit the tasks, 
 * and saving the tasks to a file, called Tasks.txt.
 * This class has a ComboBox for selecting task priorities, a ListView for displaying the tasks, 
 * and text fields for entering task descriptions and due dates. 
 *
 * @author Jalani
 * @author Che
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController implements Initializable, FileActions {

    // The ToDoList object that stores the tasks
    ToDoList list = new ToDoList();

    // The combo box for selecting task priorities
    @FXML
    private ComboBox<String> combo;

    // The list view for displaying tasks
    @FXML
    private ListView<ToDoTask> listView;

    // The text field for entering task descriptions
    @FXML
    private TextField Tasks;

    // The text field for entering task due dates
    @FXML
    private TextField DuedateForTask;

    @FXML
    private Button taskCompletedButton; // The button to mark the task as completed.

    // The "Save and Close" button
    @FXML
    private Button SaveAndClose;

    // The observable list of tasks displayed in the list view
    ObservableList<ToDoTask> taskList = FXCollections.observableArrayList(list.getTasks());

    // The filename for saving the task list
    String taskFile = "Tasks.txt";
    String completedTaskFile = "CompletedTasks.txt";

    // This method handles "Complete" button click event. Opens a confirmation to
    // confirm removing task
    @FXML
    void handleCompleteButton(ActionEvent event) {
        ToDoTask selectedTask = listView.getSelectionModel().getSelectedItem();
        int index = taskList.indexOf(selectedTask);

        // Open confirmation window if task is selected
        if (selectedTask == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No task selected!");
            alert.showAndWait();
            return;
        } else {
            // Create alert to confirm completing the task
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm Completion");
            alert.setHeaderText("Are you sure this task is complete?");
            alert.setContentText("Click OK to complete the task.");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // Remove the completed task from the taskList
                taskList.remove(index);
                // Append the completed task to the completedTaskFile
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(completedTaskFile, true))) {
                    writer.write(selectedTask.toString() + "\n");
                } catch (IOException e) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Failed to write to the file!");
                    error.showAndWait();
                    return;
                }
            } else {
                // Close the window without removing the task
                alert.close();
            }
        }
    }

    // This method handles "Edit" button click event. Opens a new window for editing
    // the selected task.
    @FXML
    void handleEditButton(ActionEvent event) throws IOException {
        ToDoTask selectedTask = listView.getSelectionModel().getSelectedItem();
        int index = taskList.indexOf(selectedTask);

        // Show an error message if no task is selected
        if (selectedTask == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No task selected!");
            alert.showAndWait();
            return;
        }

        // Load the edit window FXML file and set its controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPageView.fxml"));
        Parent root = loader.load();

        EditPageController controller = loader.getController();
        controller.setData(index, selectedTask);
        controller.setTaskList(taskList); // set the taskList in the edit controller

        // Set the scene and how the edit window
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Edit Task");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.showAndWait();

        // Refresh the task list after editing
        listView.refresh();
    }

    /**
     * This method handles the "Submit" button click event. Adds a new task to the
     * list.
     * Shows an error message if any of the task fields are empty.
     */
    @FXML
    void handleAddTaskButton(ActionEvent event) {
        Alert badinfo = new Alert(AlertType.ERROR);
        badinfo.setHeaderText("Field(s) Unselected");
        badinfo.setContentText("Please enter Task and Due Date");
        String desc = Tasks.getText();
        String dueDate = DuedateForTask.getText();
        String priority = combo.getValue();

        // Show an error message if any of the task fields are empty
        if (desc == "" || dueDate == "") {
            badinfo.showAndWait();
        } else {
            // Create a new task and add it to the list
            ToDoTask newTask = new ToDoTask(desc, dueDate, priority);
            list.addTask(newTask);
            taskList.add(newTask);

            // Clear the task input fields
            Tasks.clear();
            DuedateForTask.clear();
            combo.setValue("Low");
        }
    }

    /**
     * This method handles the Save and Close button event. It saves the ToDoList to
     * the file with the given filename and closes the window.
     */
    @FXML
    void handleSaveAndClose(ActionEvent event) {
        save(taskFile, taskList);
        Stage stage = (Stage) SaveAndClose.getScene().getWindow();
        stage.close();
    }

    // This method initializes the GUI elements.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // List view
        ObservableList<String> priorities = FXCollections.observableArrayList(
                // Items for listview
                "High",
                "Medium",
                "Low");
        // setting combo and list view items
        combo.setItems(priorities);
        combo.setValue("Low");
        listView.setItems(taskList);
    }

    /**
     * This method saves the ToDoList to the given file.
     * The tasks are not appended, but overwritten whenever saved.
     */
    @Override
    public void save(String filename, List<ToDoTask> taskList) {
        // Uses try catch to write to the file and use for loop to write to the file and
        // Next line to separte each task
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (ToDoTask task : taskList) {
                writer.write(task.toString());
                writer.newLine();
            }
            // Prints to console when it's done
            System.out.println("ToDoList saved to file: " + filename);
        } catch (IOException e) {
            // Gives error message if something went wrong
            System.err.println("Error saving ToDoList to file: " + filename);
            e.printStackTrace();
        }
    }
}