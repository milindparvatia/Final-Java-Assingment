
package controller.mainWindow;

import controller.DatabaseController;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController extends MainController implements Initializable {

    @FXML
    private Text currentUserID;
    @FXML
    private GridPane mainGrid;
    @FXML
    private Button closeButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ComboBox filterType;
    @FXML
    private ComboBox filterStatus;
    @FXML
    private ComboBox filterCreator;

    private ArrayList<Student> StudentArray;
    private HashMap<String, Post> PostMap;
    private ArrayList<Reply> Replies;
    private Student student;
    private int[] counter;

    public void addDataToDatabase() {
        DatabaseController.addPosts(PostMap);
        DatabaseController.addCounter(counter);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void paasingStudent(ArrayList<Student> studentArray, HashMap<String, Post> postMap, int[] counter, @NotNull Student student) {
        StudentArray = studentArray;
        PostMap = postMap;
        this.counter = counter;
        this.student = student;
        System.out.println(student.getSid());

        currentUserID.setText(student.getSid());
        ShowAllPost();
        setupComboBoxFilter();
    }

    private void setupComboBoxFilter() {
        filterType.getItems().addAll("ALL", "EVENTS", "SALES", "JOBS");
        filterStatus.getItems().addAll("ALL", "OPEN", "CLOSE");
        filterCreator.getItems().addAll("ALL", "My Post");

        filterType.setValue("ALL");
        filterStatus.setValue("ALL");
        filterCreator.setValue("ALL");
    }

    @FXML
    void filterTypes(ActionEvent event) {
        System.out.println("Its working.......");

        filterType.getValue();
        filterStatus.getValue();
        filterCreator.getValue();
        Map<String, Post> filtered = null;

        if(!filterType.getValue().equals("ALL")){
            filtered = PostMap.entrySet().stream()
                    .filter(x -> x.getKey().contains(filterType.getValue().toString().subSequence(0, 3)))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            HashMap<String, Post> PostMapCopy = PostMap;
            PostMap = (HashMap<String, Post>) filtered;
            mainGrid.getChildren().clear();
            ShowAllPost();
            mainGrid.setGridLinesVisible(true);
            PostMap = PostMapCopy;

        } else if(filterType.getValue().equals("ALL")){
            mainGrid.getChildren().clear();
            ShowAllPost();
            mainGrid.setGridLinesVisible(true);
        }
    }

    @FXML
    void filterStatus(ActionEvent event) {

        filterType.getValue();
        filterStatus.getValue();
        filterCreator.getValue();
        Map<String, Post> filtered = null;

        if(!filterStatus.getValue().equals("ALL")){
            System.out.println("qqqqqq"+filterStatus.getValue());
            filtered = PostMap.entrySet().stream()
                    .filter(x -> x.getValue().getStatus().equals(String.valueOf(filterStatus.getValue())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            System.out.println(filtered);
            System.out.println("Its working.......");
            HashMap<String, Post> PostMapCopy = PostMap;
            PostMap = (HashMap<String, Post>) filtered;
            mainGrid.getChildren().clear();
            ShowAllPost();
            PostMap = PostMapCopy;

        } else if(filterType.getValue().equals("ALL")){
            mainGrid.getChildren().clear();
            ShowAllPost();
        }
        mainGrid.setGridLinesVisible(true);
    }

    @FXML
    void filterCreator(ActionEvent event) {
        System.out.println("Its working.......");

        filterType.getValue();
        filterStatus.getValue();
        filterCreator.getValue();
        Map<String, Post> filtered = null;

        if(!filterCreator.getValue().equals("ALL")){
            filtered = PostMap.entrySet().stream()
                    .filter(x -> x.getValue().getCreatorID().getSid().equalsIgnoreCase(student.getSid()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            HashMap<String, Post> PostMapCopy = PostMap;
            PostMap = (HashMap<String, Post>) filtered;
            mainGrid.getChildren().clear();
            mainGrid.setGridLinesVisible(true);
            ShowAllPost();
            PostMap = PostMapCopy;

        } else if(filterType.getValue().equals("ALL")){
            mainGrid.getChildren().clear();
            mainGrid.setGridLinesVisible(true);
            ShowAllPost();
        }

    }

    @FXML
    public void newPostEvent(ActionEvent event) throws IOException {
        callingCreatePost(event, "view/EventPost_Form.fxml");
    }

    @FXML
    public void newPostSale(ActionEvent event) throws IOException {
        callingCreatePost(event, "view/SalesPost_Form.fxml");
    }

    @FXML
    public void newPostJob(ActionEvent event) throws IOException {
        callingCreatePost(event, "view/JobPost_Form.fxml");
    }

    public void ShowAllPost() {
        var index = new Object() {
            int i = 0;
            int j = 0;
        };

        PostMap.forEach((k, v) -> {

            File file = new File(v.getImageURL());
            ImageView postImage = new ImageView(new Image(file.toURI().toString()));
            String postId = null;
            Button reply = new Button();
            Button moreDetails = new Button("More Details");

            postImage.setFitHeight(100);
            postImage.setFitWidth(100);

            mainGrid.setVgap(20);
            mainGrid.setHgap(0);
            mainGrid.setAlignment(Pos.CENTER);
            mainGrid.setPrefWidth(800);

            index.i = index.i++;
            if (k.contains(getPrefixEvent())) {
                postId = setupEventInGrid(index.i, index.j, postImage, reply, moreDetails, (PostEvent) v);
                index.i = index.i +5;
                index.j++;

            } else if (k.contains(getPrefixSale())) {
                postId = setupSaleInGrid(index.i, index.j, postImage, reply, moreDetails, (PostSale) v);
                index.i = index.i +4;
                index.j++;

            } else if (k.contains(getPrefixJob())) {
                postId = setupJobInGrid(index.i, index.j, postImage, reply, moreDetails, (PostJob) v);
                index.i = index.i +3;
                index.j++;
            } else {
                System.out.println("\n----------------------------\n");
            }
            ButtonSetOnActions(reply, moreDetails, postId);
        });
    }

    private void ButtonSetOnActions(@NotNull Button reply, @NotNull Button moreDetails, String postId) {
        String finalPostId = postId;
        moreDetails.setOnAction(event -> {
                try {
                    showMoreDetails(finalPostId, event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });

        reply.setOnAction(event -> {
            try {
                replyToPost(reply.getText(), finalPostId, event);
            } catch (IOException | InputErorrException e) {
                e.printStackTrace();
            }
        });

        if(!PostMap.get(postId).getCreatorID().getSid().equals(student.getSid())){
            moreDetails.setVisible(false);
        }
    }

    private String setupJobInGrid(int i, int j, ImageView postImage, @NotNull Button reply, Button moreDetails, @NotNull PostJob job) {
        Label id = new Label("ID: "+job.getId());
        Label title = new Label("Title: "+job.getTitle());
        Label description = new Label("Description: "+job.getDescription());
        Label creatorID = new Label("CreatorID: "+job.getCreatorID().getSid());
        Label status = new Label("Status: "+job.getStatus());
        Label proposed_price = new Label("Propose Price: "+ job.getPropose_price());
        Label lowest_offer = new Label("Lowest Offer: "+ job.getLowest_offer());
        reply.setText("Reply");

        GridPane.setRowSpan(postImage, 4);
        GridPane.setConstraints(postImage,      0, i);
        GridPane.setConstraints(id,             1, i);
        GridPane.setConstraints(title,          1, i+1);
        GridPane.setConstraints(proposed_price, 1, i +2);
        GridPane.setConstraints(description,    2, i+1);
        GridPane.setConstraints(status,         1, i+3);
        GridPane.setConstraints(creatorID,      2, i);
        GridPane.setConstraints(lowest_offer,   2, i +2);
        GridPane.setConstraints(reply,          3, i);
        GridPane.setConstraints(moreDetails,    3, i +2);

        GridPane newGrid = newGrid();
        newGrid.setStyle("-fx-background-color: #FFFFE0; -fx-text-fill: white;");
        newGrid.getChildren().addAll(postImage, id, title, description, creatorID, status, proposed_price, lowest_offer, moreDetails, reply);
        mainGrid.addRow(j, newGrid);
        return job.getId();
    }

    private String setupSaleInGrid(int i, int j, ImageView postImage, @NotNull Button reply, Button moreDetails, @NotNull PostSale sale) {

        GridPane newGrid = newGrid();

        Label id = new Label("ID: "+sale.getId());
        Label title = new Label("Title: "+sale.getTitle());
        Label description = new Label("Description: "+sale.getDescription());
        Label creatorID = new Label("CreatorID: "+sale.getCreatorID().getSid());
        Label status = new Label("Status: "+sale.getStatus());
        Label highest_offer  = new Label("Highest Offer: "+ sale.getHighest_offer());
        Label minimum_raise = new Label("Minimum Raise: "+ sale.getMinimum_raise());
        Label asking_price  = new Label("Asking Price"+ sale.getAsking_price());
        reply.setText("Reply");

        GridPane.setRowSpan(postImage, 4);
        GridPane.setConstraints(postImage,      0, i);
        GridPane.setConstraints(id,             1, i);
        GridPane.setConstraints(title,          1, i+1);
        GridPane.setConstraints(highest_offer,  1, i+2);
        GridPane.setConstraints(status,         1, i+3);
        GridPane.setConstraints(creatorID,      2, i);
        GridPane.setConstraints(description,    2, i+1);
        GridPane.setConstraints(minimum_raise,  2, i+2);
        GridPane.setConstraints(reply,          3, i);
        GridPane.setConstraints(moreDetails,    3, i +2);

        if(sale.getCreatorID().getSid().equals(student.getSid())) {
            System.out.println("Student id"+student.getSid());
            GridPane.setConstraints(asking_price, 2, i + 3);
            newGrid.getChildren().addAll(asking_price);
        }
        newGrid.setStyle("-fx-background-color:#ffb6c1; -fx-text-fill: white;");
        newGrid.getChildren().addAll(postImage, id, title, description, creatorID, status, highest_offer, minimum_raise,moreDetails, reply);
        mainGrid.addRow(j, newGrid);
        return sale.getId();
    }

    private String setupEventInGrid(int i, int j, ImageView postImage, @NotNull Button reply, Button moreDetails, @NotNull PostEvent event) {
        Label id = new Label("ID: "+event.getId());
        Label title = new Label("Title: "+event.getTitle());
        Label description = new Label("Description: "+event.getDescription());
        Label creatorID = new Label("CreatorID: "+event.getCreatorID().getSid());
        Label status = new Label("Status: "+event.getStatus());
        Label venue = new Label("Venue: "+event.getVenue());
        Label date = new Label("Date: "+event.getDate());
        Label capacity = new Label("Event Capacity: "+ event.getCapacity());
        Label attendee_count = new Label("Attendee Counts: "+ event.getAttendee_count());
        reply.setText("Join");

        GridPane.setRowSpan(postImage, 4);
        GridPane.setConstraints(postImage,  0, i);
        GridPane.setConstraints(id,         1, i);
        GridPane.setConstraints(title,      1, i+1);
        GridPane.setConstraints(creatorID,  1, i+2);
        GridPane.setConstraints(description,1, i+3);
        GridPane.setConstraints(attendee_count,1, i+4);
        GridPane.setConstraints(status,     2, i);
        GridPane.setConstraints(venue,      2, i+1);
        GridPane.setConstraints(date,       2, i+2);
        GridPane.setConstraints(capacity,   2, i+3);
        GridPane.setConstraints(reply,      3, i);
        GridPane.setConstraints(moreDetails,3, i +2);

        GridPane newGrid = newGrid();
        newGrid.setStyle("-fx-background-color: #e0ffff; -fx-text-fill: white;");
        newGrid.getChildren().addAll(postImage, id, title, description, creatorID, status, venue, date, capacity, attendee_count, moreDetails, reply);
        mainGrid.addRow(j, newGrid);
        return event.getId();
    }

    private @NotNull GridPane newGrid() {
        GridPane grid= new GridPane();

        ColumnConstraints cc = new ColumnConstraints(150, 150, 300, Priority.ALWAYS, HPos.LEFT, true);
        cc.setFillWidth(true);
        grid.getColumnConstraints().addAll(cc,cc,cc,cc);
        grid.setMinWidth(600);
        return grid;
    }

    @FXML
    void replyToPost(@NotNull String replyButton, String postId, ActionEvent event) throws IOException, InputErorrException {

        if (replyButton.equals("Reply")){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ReplyPost.fxml"));
            // load the fxml file
            Parent root = loader.load();

            Scene MainScene = new Scene(root);
            //Here I want to swap the screen!

            ReplyController controller = loader.getController();
            controller.pasingReply(StudentArray, PostMap, counter, student, postId);

            Stage stage = new Stage();
            stage.setScene(MainScene);
            stage.show();
        } else {
            try {
                if(!PostMap.get(postId).getCreatorID().getSid().equals(student.getSid())) {
                    Reply reply = new Reply(PostMap.get(postId), 0, student);
                    if (PostMap.get(postId).handleReply(reply)){
                        DatabaseController.updatePostReplies(reply);
                    }

                    errorDialog("You have Join "+PostMap.get(postId).getId(), "INFO");
                } else {
                    errorDialog("As the creator of post "+PostMap.get(postId).getId()+" you can't join this Event", "ERROR");
                }
            } catch (Throwable e) {
                if(e.getMessage().contains("already submited your offer")){
                    errorDialog("Request denied!, You have already joined "+PostMap.get(postId).getId()+" Event", "ERROR");
                } else if(e.getMessage().contains("this event has no seat left")){
                    errorDialog("Request denied!, The Event "+PostMap.get(postId).getId()+" has no seat left", "ERROR");
                }
                System.out.println("\n**" + e.getMessage() + "**");
        }
        }
    }

    @FXML
    void showMoreDetails(String postId, @NotNull ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PostDetail_screen.fxml"));
        // load the fxml file
        Parent root = loader.load();
        // loading like this, we can get access to the controller
        PostDetailsController controller = loader.getController();
        controller.paasingPostDetails(StudentArray, PostMap, counter, student, postId);

        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(MainScene);
        stage.show();
        }

    private void callingCreatePost(@NotNull ActionEvent event, String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(s));
        // load the fxml file
        Parent root = loader.load();
        // loading like this, we can get access to the controller
        CreatePosts controller = loader.getController();
        controller.valuePassing(StudentArray, PostMap, counter, student);

        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(MainScene);
        stage.show();
    }

    @FXML
    public void closingStage(@NotNull ActionEvent event) throws IOException {
        addDataToDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/sample.fxml"));
        // load the fxml file
        Parent root = loader.load();
        // loading like this, we can get access to the controller
        Student newStudent = new Student();
        MainController controller = loader.getController();
        controller.initializeModel(StudentArray, PostMap, counter, newStudent);

        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(MainScene);
        stage.show();
    }

    @FXML
    public void onQuit(ActionEvent event) {
        addDataToDatabase();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    public void fileExport(ActionEvent event) throws IOException {

        File f = new File("src/data/export_data.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(StudentArray);
        oos.writeObject(PostMap);
        oos.writeObject(counter);
        oos.flush();
        oos.close();
        errorDialog("Data has been Exported", "INFO");

    }

    @FXML
    public void fileImport(ActionEvent event) throws IOException, ClassNotFoundException {

        Stage stage = (Stage) menuBar.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.TXT)", "*.TXT");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

                StudentArray = (ArrayList<Student>) ois.readObject();
                PostMap = (HashMap<String, Post>) ois.readObject();
                counter = (int[]) ois.readObject();
                ois.close();
                errorDialog("Data has been Imported from selected file", "INFO");
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        addDataToDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main_screen.fxml"));
        // load the fxml file
        Parent root = loader.load();
        // loading like this, we can get access to the controller
        MainWindowController controller = loader.getController();
        controller.paasingStudent(StudentArray, PostMap, counter, student);

        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        stage.setScene(MainScene);
        stage.show();
    }

    @FXML
    public void openDevInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/dev_info.fxml"));
        // load the fxml file
        Parent root = loader.load();

        Scene MainScene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(MainScene);
        stage.show();
    }
}
