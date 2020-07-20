import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Post;
import model.Reply;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    ArrayList<Student> StudentArray;
    HashMap<String, Post> PostMap;
    ArrayList<Reply> Replies;
    int[] Counter;
    Student student;


    @Override
    public void start(Stage primaryStage) throws Exception {
        StudentArray = new ArrayList<>();
        PostMap = new HashMap<>();
        Replies = new ArrayList<>();
        Counter = new int[]{0, 0, 0};
        student = new Student();

        eventHandlingExample1(primaryStage, StudentArray, PostMap, Replies, Counter, student);
    }

    public void stop() {
        System.out.println("stage closing");

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/main_screen.fxml"));
//        // load the fxml file
//        Parent root = loader.load();
//        MainWindowController controller = loader.getController();
//        controller.addDataToDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void eventHandlingExample1(Stage primaryStage, ArrayList StudentArray, HashMap PostMap, ArrayList<Reply> Replies, int[] Counter, Student student) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/sample.fxml"));

            // load the fxml file
            Parent root = loader.load();

            // loading like this, we can get access to the controller

//            MainController controller = loader.getController();
//            controller.initializeModel(StudentArray, PostMap, Counter, student);

            System.out.println("Stage is opening");
            primaryStage.setTitle("Unilink System");
            primaryStage.setScene(new Scene(root));

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}