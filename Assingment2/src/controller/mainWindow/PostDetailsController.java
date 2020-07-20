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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PostDetailsController extends MainController implements Initializable {

    @FXML
    private Label titleOfPostDetails;
    @FXML
    private TableView replyTable;
    @FXML
    private GridPane postDetailGrid;
    @FXML
    private ImageView imageUpload;
    @FXML
    private Button imageUploadButton;

    private ArrayList<Student> StudentArray;
    private HashMap<String, Post> PostMap;
    private ArrayList<Reply> Replies;
    private Student student;
    private int[] counter;
    private String mapId;

    private TextField tf_title;
    private TextField tf_description;
    private TextField tf_venue;
    private TextField tf_date;
    private TextField tf_capacity;
    private TextField tf_attendee_count;
    private TextField tf_highest_offer;
    private TextField tf_minimum_raise;
    private TextField tf_asking_price;
    private TextField tf_proposed_price;
    private TextField tf_lowest_offer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void paasingPostDetails(ArrayList<Student> studentArray, HashMap<String, Post> postMap, int[] counter, Student student, String mapId) {
        StudentArray = studentArray;
        PostMap = postMap;
        this.counter = counter;
        this.student = student;
        this.mapId = mapId;
        setupImage();
        setupReplyTable();
        setupPostDetails();
        titleOfPostDetails.setAlignment(Pos.CENTER);
    }

    private void setupImage() {
        File file = new File(PostMap.get(mapId).getImageURL());
        Image image = new Image(file.toURI().toString());
        imageUpload.setImage(image);
    }

    private void setupPostDetails() {

        String postID = PostMap.get(mapId).getId().toUpperCase();

        if(postID.contains("EVE")){
            setUpEventGrid();
        } else if(postID.contains("SAL")){
            setUpSaleGrid();
        } else if(postID.contains("JOB")){
            setUpJobGrid();
        } else {

        }

        ColumnConstraints cc = new ColumnConstraints(100, 100, 100, Priority.ALWAYS, HPos.LEFT, true);
        cc.setFillWidth(true);
        postDetailGrid.getColumnConstraints().addAll(cc,cc,cc,cc);
    }

    private void setUpEventGrid() {

        titleOfPostDetails.setText("Event Post's Details");
        PostEvent event = (PostEvent) PostMap.get(mapId);

        Label id = new Label(event.getId());
        Label creatorID = new Label(event.getCreatorID().getSid());
        Label status = new Label(event.getStatus());

        Label lb_id = new Label("ID: ");
        Label lb_title = new Label("Title: ");
        Label lb_description = new Label("Description: ");
        Label lb_creatorID = new Label("CreatorID: ");
        Label lb_status = new Label("Status: ");
        Label lb_venue = new Label("Venue: ");
        Label lb_date = new Label("Date: ");
        Label lb_capacity = new Label("Event Capacity: ");
        Label lb_attendee_count = new Label("Attendee Counts: ");

        tf_title = new TextField(event.getTitle());
        tf_description = new TextField(event.getDescription());
        tf_venue = new TextField(event.getVenue());
        tf_date = new TextField(event.getDate());
        tf_capacity = new TextField(Integer.toString(event.getCapacity()));
        tf_attendee_count = new TextField(Integer.toString(event.getAttendee_count()));

        if(event.getReplies().size()>0) {
            tf_title.setDisable(true);
            tf_description.setDisable(true);
            tf_venue.setDisable(true);
            tf_date.setDisable(true);
            tf_capacity.setDisable(true);
            tf_attendee_count.setDisable(true);
        }

        GridPane.setConstraints(lb_id,              0, 0);
        GridPane.setConstraints(lb_title,           0, 1);
        GridPane.setConstraints(lb_creatorID,       0, 2);
        GridPane.setConstraints(lb_description,     0, 3);
        GridPane.setConstraints(lb_attendee_count,  0, 4);

        GridPane.setConstraints(id,                 1, 0);
        GridPane.setConstraints(tf_title,           1, 1);
        GridPane.setConstraints(creatorID,          1, 2);
        GridPane.setConstraints(tf_description,     1, 3);
        GridPane.setConstraints(tf_attendee_count,  1, 4);

        GridPane.setConstraints(lb_status,          2, 0);
        GridPane.setConstraints(lb_venue,           2, 1);
        GridPane.setConstraints(lb_date,            2, 2);
        GridPane.setConstraints(lb_capacity,        2, 3);

        GridPane.setConstraints(status,             3, 0);
        GridPane.setConstraints(tf_venue,           3, 1);
        GridPane.setConstraints(tf_date,            3, 2);
        GridPane.setConstraints(tf_capacity,        3, 3);

        postDetailGrid.getChildren().addAll(id, tf_title, tf_description, tf_attendee_count, tf_venue, tf_date, tf_capacity,
                lb_id,lb_title, lb_creatorID, lb_description, lb_attendee_count,
                creatorID,  lb_status, lb_venue, lb_date, lb_capacity, status);
        postDetailGrid.setStyle("-fx-background-color: #e0ffff; -fx-text-fill: white;");
        postDetailGrid.setGridLinesVisible(true);
    }

    private void setUpSaleGrid() {

        titleOfPostDetails.setText("Sale Post's Details");
        PostSale event = (PostSale) PostMap.get(mapId);

        Label id = new Label(event.getId());
        Label creatorID = new Label(event.getCreatorID().getSid());
        Label status = new Label(event.getStatus());

        Label lb_id = new Label("ID: ");
        Label lb_title = new Label("Title: ");
        Label lb_description = new Label("Description: ");
        Label lb_creatorID = new Label("CreatorID: ");
        Label lb_status = new Label("Status: ");

        Label lb_highest_offer = new Label("Highest Offer: ");
        Label lb_minimum_raise = new Label("Minimum Raise: ");
        Label lb_asking_price = new Label("Event Capacity: ");

        tf_title = new TextField(event.getTitle());
        tf_description = new TextField(event.getDescription());
        tf_highest_offer = new TextField(String.valueOf(event.getHighest_offer()));
        tf_minimum_raise = new TextField(Float.toString(event.getMinimum_raise()));
        tf_asking_price = new TextField(Float.toString(event.getAsking_price()));

        if(event.getReplies().size()>0) {
            tf_title.setDisable(true);
            tf_description.setDisable(true);
            tf_highest_offer.setDisable(true);
            tf_minimum_raise.setDisable(true);
            tf_asking_price.setDisable(true);
        }

        GridPane.setConstraints(lb_id,              0, 0);
        GridPane.setConstraints(lb_title,           0, 1);
        GridPane.setConstraints(lb_creatorID,       0, 2);
        GridPane.setConstraints(lb_description,     0, 3);

        GridPane.setConstraints(id,                 1, 0);
        GridPane.setConstraints(tf_title,           1, 1);
        GridPane.setConstraints(creatorID,          1, 2);
        GridPane.setConstraints(tf_description,     1, 3);

        GridPane.setConstraints(lb_status,          2, 0);
        GridPane.setConstraints(lb_highest_offer,   2, 1);
        GridPane.setConstraints(lb_minimum_raise,   2, 2);
        GridPane.setConstraints(lb_asking_price,    2, 3);

        GridPane.setConstraints(status,             3, 0);
        GridPane.setConstraints(tf_highest_offer,   3, 1);
        GridPane.setConstraints(tf_minimum_raise,   3, 2);
        GridPane.setConstraints(tf_asking_price,    3, 3);

        postDetailGrid.getChildren().addAll(id, tf_title, tf_description, tf_highest_offer, tf_minimum_raise, tf_asking_price,
                lb_id,lb_title, lb_creatorID, lb_description,
                creatorID,  lb_status, lb_highest_offer, lb_minimum_raise, lb_asking_price, status);
        postDetailGrid.setStyle("-fx-background-color: #ffb6c1; -fx-text-fill: white;");
        postDetailGrid.setGridLinesVisible(true);
    }

    private void setUpJobGrid() {

        titleOfPostDetails.setText("Job Post's Details");
        PostJob job = (PostJob) PostMap.get(mapId);

        Label id = new Label(job.getId());
        Label creatorID = new Label(job.getCreatorID().getSid());
        Label status = new Label(job.getStatus());

        Label lb_id = new Label("ID: ");
        Label lb_title = new Label("Title: ");
        Label lb_description = new Label("Description: ");
        Label lb_creatorID = new Label("CreatorID: ");
        Label lb_status = new Label("Status: ");

        Label lb_proposed_price = new Label("Propose Price: ");
        Label lb_lowest_offer = new Label("Lowest Offer: ");

        tf_title = new TextField(job.getTitle());
        tf_description = new TextField(job.getDescription());
        tf_proposed_price = new TextField(String.valueOf(job.getPropose_price()));
        tf_lowest_offer = new TextField(Float.toString(job.getLowest_offer()));

        if(job.getReplies().size()>0) {
            tf_title.setDisable(true);
            tf_description.setDisable(true);
            tf_proposed_price.setDisable(true);
            tf_lowest_offer.setDisable(true);
        }

        GridPane.setConstraints(lb_id,              0, 0);
        GridPane.setConstraints(lb_title,           0, 1);
        GridPane.setConstraints(lb_creatorID,       0, 2);
        GridPane.setConstraints(lb_description,     0, 3);

        GridPane.setConstraints(id,                 1, 0);
        GridPane.setConstraints(tf_title,           1, 1);
        GridPane.setConstraints(creatorID,          1, 2);
        GridPane.setConstraints(tf_description,     1, 3);

        GridPane.setConstraints(lb_status,          2, 0);
        GridPane.setConstraints(lb_proposed_price,   2, 1);
        GridPane.setConstraints(lb_lowest_offer,   2, 2);

        GridPane.setConstraints(status,             3, 0);
        GridPane.setConstraints(tf_proposed_price,   3, 1);
        GridPane.setConstraints(tf_lowest_offer,   3, 2);

        postDetailGrid.getChildren().addAll(id, tf_title, tf_description, tf_proposed_price, tf_lowest_offer,
                lb_id,lb_title, lb_creatorID, lb_description,
                creatorID,  lb_status, lb_proposed_price, lb_lowest_offer, status);
        postDetailGrid.setStyle("-fx-background-color: #FFFFE0; -fx-text-fill: white;");
        postDetailGrid.setGridLinesVisible(true);
    }

    private void setupReplyTable() {
        System.out.println("mone");
        TableColumn<String, PostDetailsController> column1 = new TableColumn<>("Student Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        TableColumn<String, PostDetailsController> column2 = new TableColumn<>("Value");
        column2.setCellValueFactory(new PropertyValueFactory<>("value"));

        replyTable.getColumns().add(column1);

        if (!PostMap.get(mapId).getId().contains("EVE"))
            replyTable.getColumns().add(column2);

        ArrayList<? extends Reply> replies = PostMap.get(mapId).getReplies();

        for(int i = 0; i < replies.size(); i++) {
            replyTable.getItems().add(new Reply(replies.get(i).getPostID(), replies.get(i).getValue(), replies.get(i).getRespondersId()));
        }

        if(PostMap.get(mapId).getId().contains("SAL")){
            column2.setComparator(column2.getComparator().reversed());
            replyTable.getSortOrder().add(column2);
        } else if(PostMap.get(mapId).getId().contains("JOB")){
            replyTable.getSortOrder().add(column2);
        } else {

        }

    }

    @FXML
    void backToMainWindows(@NotNull ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main_screen.fxml"));
        // load the fxml file
        Parent root = loader.load();

        // loading like this, we can get access to the controller
        MainWindowController controller = loader.getController();
        controller.paasingStudent(StudentArray, PostMap, counter, student);

        Scene MainScene = new Scene(root);
        //Here I want to swap the screen!

        Stage Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage.setScene(MainScene);
        Stage.show();
    }

    @FXML
    void closePost(ActionEvent event) throws IOException {
        PostMap.get(mapId).setStatus(false);
        System.out.println("Your Post has been closeed now");
        String tableName = "";

        if(PostMap.get(mapId).getId().contains("EVE")){
            tableName = "EVENTS";
        } else if(PostMap.get(mapId).getId().contains("SAL")){
            tableName = "SALES";
        } else  if (PostMap.get(mapId).getId().contains("JOB")) {
            tableName = "JOBS";
        }
        DatabaseController.updatePost("UPDATE "+tableName+" SET status = '"+PostMap.get(mapId).getStatusForDB()+"' WHERE ID = '"+PostMap.get(mapId).getId()+"';");
        errorDialog("The Post "+PostMap.get(mapId).getId()+" has been closed", "INFO");
        backToMainWindows(event);
    }

    @FXML
    void deletePost(ActionEvent event) throws IOException {
        String tableName = "";

        if(PostMap.get(mapId).getId().contains("EVE")){
            tableName = "EVENTS";
        } else if(PostMap.get(mapId).getId().contains("SAL")){
            tableName = "SALES";
        } else  if (PostMap.get(mapId).getId().contains("JOB")){
            tableName = "JOBS";
        }
        DatabaseController.updatePost("DELETE FROM "+tableName+" WHERE ID = '"+PostMap.get(mapId).getId()+"';");
        PostMap.remove(mapId);
        errorDialog("The Post has been removed", "INFO");
        backToMainWindows(event);
    }

    @FXML
    void savePost(ActionEvent e) {
        String lb = String.valueOf(postDetailGrid.getChildren().get(0));
        String tableName = "";

        if(lb.contains("EVE")){
            tableName = saveEventPost();
        }
        else if(lb.contains("SAL")){
            tableName = saveSalesPost();
        }
        else if(lb.contains("JOB")){
            tableName = saveJobPosts();
        }
        errorDialog("The Post "+PostMap.get(mapId).getId()+" has been saved", "INFO");
        DatabaseController.updatePost("DELETE FROM "+tableName+" WHERE ID = '"+PostMap.get(mapId).getId()+"';");
    }

    @NotNull
    private String saveJobPosts() {
        String tableName;
        PostJob job = (PostJob) PostMap.get(mapId);
        job.setTitle(tf_title.getText());
        job.setDescription(tf_description.getText());
        job.setPropose_price(Float.parseFloat(tf_proposed_price.getText()));
        job.setLowest_offer(Float.parseFloat(tf_lowest_offer.getText()));
        PostMap.replace(job.getId(), job);

        tableName = "JOBS";
        return tableName;
    }

    @NotNull
    private String saveSalesPost() {
        String tableName;
        PostSale sale = (PostSale) PostMap.get(mapId);
        sale.setTitle(tf_title.getText());
        sale.setDescription(tf_description.getText());
        sale.setHighest_offer(Float.parseFloat(tf_highest_offer.getText()));
        sale.setMinimum_raise(Float.parseFloat(tf_minimum_raise.getText()));
        sale.setAsking_price(Float.parseFloat(tf_asking_price.getText()));
        PostMap.replace(sale.getId(), sale);

        tableName = "SALES";
        return tableName;
    }

    @NotNull
    private String saveEventPost() {
        String tableName;
        PostEvent event = (PostEvent) PostMap.get(mapId);
        event.setTitle(tf_title.getText());
        event.setDescription(tf_description.getText());
        event.setVenue(tf_venue.getText());
        event.setDate(tf_date.getText());
        event.setCapacity(Integer.parseInt(tf_capacity.getText()));
        event.setAttendee_count(Integer.parseInt(tf_attendee_count.getText()));
        PostMap.replace(event.getId(),event);

        tableName = "EVENTS";
        return tableName;
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
                PostMap.get(mapId).setImageURL(file.toString());
                String[] s = file.toString().split("/");
                DatabaseController.uploadImage(PostMap.get(mapId).getId(), "src/image/"+s[s.length-1]);
                FileUtils.copyFileToDirectory(file, new File("src/image/"));
                errorDialog("The Image "+file+" has been Uploaded", "INFO");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
