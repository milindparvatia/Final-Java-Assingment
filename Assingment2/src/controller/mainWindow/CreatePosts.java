package controller.mainWindow;

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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreatePosts extends MainController implements Initializable {

    @FXML
    public Button eventSubmit;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField venue;
    @FXML
    private TextField date;
    @FXML
    private TextField capacity;
    @FXML
    private TextField asking_price;
    @FXML
    private TextField minimum_price;
    @FXML
    private TextField propose_price;
    @FXML
    private ImageView imageUpload;
    @FXML
    private Button imageUploadButton;


    private ArrayList<Student> StudentArray;
    private HashMap<String, Post> PostMap;
    private int[] counter;
    private Student student;
    private String imageUrl;

    // New Post Creation for event, sales, jobs
    public void newEventPost(ActionEvent event) {

        try {
            Date dateConverted = new SimpleDateFormat("dd/MM/yyyy").parse(date.getText());
            String dateString = new SimpleDateFormat("dd/MM/yyyy").format(dateConverted);

            String id = CreateNewID(counter[0], getPrefixEvent());

            Post  newPost = new PostEvent();
            newPost.
//                    new PostEvent(id, name.getText(), description.getText(), StudentArray.get(0), true,
//                    imageUrl, venue.getText(), dateString, Integer.parseInt(capacity.getText()), 0);
//            PostMap.put(id, newPost);

            errorDialog("Success! Your Event has been created with event id : " + id, "INFO");
            System.out.println("Success! Your Event has been created with event id : " + id);
            eventToHome(event);

        } catch (ParseException | IOException e) {
            errorDialog(e.getMessage() + ", Please enter date in dd/mm/yyy format !!", "ERROR");
            System.out.println("\n** " + e.getMessage() + ", Please enter date in dd/mm/yyy format **");
        }
    }

    public void newSalePost(ActionEvent event) {

        try {
            System.out.println("Enter Details of the Sales below: ");

            if ((Integer.parseInt(asking_price.getText()) <= 0) || (Integer.parseInt(minimum_price.getText()) <= 0)){
                throw new InputErorrException("Asking Price and Minimum Raise should be greater than 0");
            }

            String id = CreateNewID(counter[2], getPrefixSale());

            PostMap.put(id, new PostSale(id, name.getText(), description.getText(), student, true,
                    imageUrl, Integer.parseInt(asking_price.getText()),
                    0, Integer.parseInt(minimum_price.getText())));

            eventToHome(event);

            System.out.println("Success! Your Event has been created with event id : " + id);
            errorDialog("Success! Your Event has been created with event id : " + id, "INFO");
        } catch (Exception e) {
            System.out.println("\n** " + e.getMessage() + " **");
            errorDialog(e.getMessage(), "ERROR");
        }
    }

    public void newJobPost(ActionEvent event) {
        try {
            if (Integer.parseInt(propose_price.getText()) <= 0) {
                throw new InputErorrException("Propose Price should be greater than 0");
            }

            String id = CreateNewID(counter[1], getPrefixJob());
            PostMap.put(id, new PostJob(id, name.getText(), description.getText(), student, true,
                    imageUrl, Integer.parseInt(propose_price.getText()), 0));

            eventToHome(event);

            System.out.println("Success! Your Event has been created with event id : " + id);
            errorDialog("Success! Your Event has been created with event id : " + id, "INFO");
        } catch (Exception e) {
            errorDialog(e.getMessage() + " **", "ERROR");
            System.out.println("\n** " + e.getMessage() + " **");
        }
    }

    private void eventToHome(@NotNull ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main_screen.fxml"));
        // load the fxml file
        Parent root = loader.load();

        MainWindowController controller = loader.getController();
        controller.paasingStudent(StudentArray, PostMap, counter, student);

        MainController controller1 = loader.getController();
        controller1.initializeModel(StudentArray, PostMap, counter, student);

        System.out.println("posting2"+ PostMap);
        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(MainScene);
        stageTheEventSourceNodeBelongs.show();
    }

    public String CreateNewID(int value, String prefixEvent) {
        try {
            int id_size = value + 1;

            String id = (id_size < 10 ? "00" : id_size < 100 ? "0" : "") + id_size;

            if (prefixEvent.equals(getPrefixEvent())) {
                counter[0]++;
                return getPrefixEvent() + id;
            } else if (prefixEvent.equals(getPrefixJob())) {
                counter[1]++;
                return getPrefixJob() + id;
            } else if (prefixEvent.equals(getPrefixSale())) {
                counter[2]++;
                return getPrefixSale() + id;
            }
            throw new InputErorrException("Internal error in creating ID");
        } catch (Exception e) {
            errorDialog("Internal error in creating ID", "ERROR");
            System.out.println("\n** " + e.getMessage() + " **");
        }
        return "";
    }

    public void valuePassing(ArrayList<Student> studentArray, HashMap<String, Post> postMap, int[] counter, @NotNull Student student) {
        StudentArray = studentArray;
        PostMap = postMap;
        this.counter = counter;
        this.student = student;
        imageUrl = "src/image/noImageAvailable.png";
        System.out.println(student.getSid());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void uploadPostImage(@NotNull ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                String[] s = file.toString().split("/");
                imageUrl = "src/image/"+s[s.length-1];
                FileUtils.copyFileToDirectory(file, new File("src/image/"));
                errorDialog("The Image "+file+" has been Uploaded", "INFO");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}