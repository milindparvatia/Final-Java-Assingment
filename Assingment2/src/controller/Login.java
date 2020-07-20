package controller;

import controller.mainWindow.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Post;
import model.Student;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static controller.DatabaseController.getAllReplies;

public class Login extends MainController {

    @FXML
    private TextField username;

    private ArrayList<Student> StudentArray;

    @FXML
    public boolean LoginEvent(ActionEvent event) {
        try {
            if (username.getText().startsWith("s")) {
                if (username.getText().matches("s[0-9]+") && username.getText().length() > 0) {

                    StudentArray = DatabaseController.getStudents();

                    Student element;

                    for (Student student : Objects.requireNonNull(StudentArray)) {
                        System.out.println(student.getSid());
                        element = student;

                        if (username.getText().equals(element.getSid())) {
                            System.out.println("\nAccount found\n");
                            ChangeScreen(event, element);
                            return true;
                        }
                    }

                    System.out.println("\nAccount not found..!! \n\nCreating new Account from this Username :)");
                    element = new Student(username.getText());
                    DatabaseController.addStudent(element);
                    StudentArray.add(element);
                    ChangeScreen(event, element);
                    return true;
                } else {
                    errorDialog("Username should follow by 's' with numbers only!!", "ERROR");
                }} else {
                errorDialog("Username can only starts with small 's'!!", "ERROR");
            }

        } catch (Throwable e) {
            System.out.println("\n**" + e.getMessage() + "**");
        }
        return false;
    }

    public void ChangeScreen(@NotNull ActionEvent event, Student student) throws Throwable {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main_screen.fxml"));

        // load the fxml file
        Parent root = loader.load();
        HashMap<String, Post> postMap = getAllReplies(DatabaseController.getPosts(), StudentArray);
        System.out.println("All my postsssss "+DatabaseController.getPosts());
        int @Nullable [] counter = DatabaseController.getCounterArray();

        // loading like this, we can get access to the controller
        MainController controller1 = loader.getController();
        controller1.initializeModel(StudentArray, postMap, counter, student);

        MainWindowController controller = loader.getController();
        controller.paasingStudent(StudentArray, postMap, counter, student);

        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(MainScene);
        stage.show();
    }
}
