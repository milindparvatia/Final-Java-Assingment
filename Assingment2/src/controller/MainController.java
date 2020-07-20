package controller;

import javafx.scene.control.Alert;
import model.Post;
import model.Reply;
import model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainController {

    // I have created arraylist for student since its easy to keep track and no
    // further mapping is required at the moment.
    // for Posts I have created HashMap which binds Post ID as key and Post object
    // as value of Hashmap

    private ArrayList<Student> StudentArray;
    private HashMap<String, Post> PostMap;
    private ArrayList<Reply> replies;

    private Student student;
    // I have created counter for managing posts number and creating automatic ID
    // for post
    private int[] counter;

    private final static String PREFIX_EVENT = "EVE";
    private final static String PREFIX_JOB = "JOB";
    private final static String PREFIX_SALE = "SAL";

    public MainController() {
    }

    //getter setter
    public ArrayList<Student> getStudentArray() {
        return StudentArray;
    }

    public void setStudentArray(ArrayList<Student> studentArray) {
        StudentArray = studentArray;
    }

    public HashMap<String, Post> getPostMap() {
        return PostMap;
    }

    public void setPostMap(HashMap<String, Post> postMap) {
        PostMap = postMap;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Reply> replies) {
        this.replies = replies;
    }

    public int[] getCounter() {
        return counter;
    }

    public void setCounter(int[] counter) {
        this.counter = counter;
    }

    public static String getPrefixEvent() {
        return PREFIX_EVENT;
    }

    public static String getPrefixJob() {
        return PREFIX_JOB;
    }

    public static String getPrefixSale() {
        return PREFIX_SALE;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void initializeModel(ArrayList StudentArray, HashMap PostMap, int[] counter, Student username) {
        this.counter = counter;
        this.PostMap = PostMap;
        this.StudentArray = StudentArray;
        this.student = student;
//        this.replies = Replies;

        callingArray(StudentArray, PostMap, counter, username);
    }

    private void callingArray(ArrayList studentArray, HashMap postMap, int[] counter, Student student) {
        System.out.println(
                "printing from MainC: " +studentArray+
                        " post:  "      +postMap+
//                        "rep "          +Replies+
                        " counter:  "   + Arrays.toString(counter) +
                        " student: "    +student
        );
    }

    public void errorDialog(String msg, String type){
        if (type.equals("ERROR")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Looks like there might be an Error!");
            alert.setContentText(msg);
            alert.showAndWait();
        } else if(type.equals("INFO")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        }

    }
}
