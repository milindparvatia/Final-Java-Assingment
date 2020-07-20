package controller.mainWindow;

import controller.DatabaseController;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InputErorrException;
import model.Post;
import model.Reply;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReplyController extends MainController implements Initializable {

    private ArrayList<Student> StudentArray;
    private HashMap<String, Post> PostMap;
    private ArrayList<Reply> Replies;
    private Student student;
    private int[] counter;
    private String mapId;

    @FXML
    private Button offerSubmit;
    @FXML
    private TextField offerValue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void pasingReply(ArrayList<Student> studentArray, HashMap<String, Post> postMap, int[] counter, Student student, String postId) {
        StudentArray = studentArray;
        PostMap = postMap;
        this.counter = counter;
        this.student = student;
        this.mapId = postId;
        System.out.println(mapId);
    }

    @FXML
    void submitPostReply(ActionEvent event) throws IOException, InputErorrException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main_screen.fxml"));
        // load the fxml file
        Parent root = loader.load();
        // loading like this, we can get access to the controller

        Reply reply = new Reply(PostMap.get(mapId), Float.parseFloat(offerValue.getText()), student);
        if (PostMap.get(mapId).handleReply(reply)){
            DatabaseController.updatePostReplies(reply);
        }

        System.out.println("size is: "+PostMap.get(mapId).getReplies().size());
        MainWindowController controller = loader.getController();
        controller.paasingStudent(StudentArray, PostMap, counter, student);

        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(MainScene);
        stage.show();

        // get a handle to the stage
        Stage stageHandle = (Stage) MainScene.getWindow();
        // do what you have to do
        stageHandle.close();

        System.out.println(PostMap.size());
    }
}
