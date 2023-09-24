
/**
*The ToDoListGUI class is a JavaFX application that represents The Main GUI for the to-do list application. 
*It loads the FXML file that defines the layout and elements of the user interface, and creates a window and displays it.
*@author Jalani
*@author Che
*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToDoListGUI extends Application {

    /**
     * The start() method is the entry point of the JavaFX application. It loads the
     * FXML file that defines the layout and
     * elements of the user interface, creates a new scene with the loaded FXML
     * root, sets the scene to the primary stage and
     * shows the stage.
     *
     * @param primaryStage the primary stage of the JavaFX application
     * @throws IOException if there is an error loading the FXML file
     */

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Loading the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        // Creating a new scene
        Scene scene = new Scene(root, 600, 529);
        // Setting title scene and showing it
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // The main() method launches the JavaFX application by calling the launch()
    // method of the Application class.
    public static void main(String[] args) {
        launch(args);
    }
}
