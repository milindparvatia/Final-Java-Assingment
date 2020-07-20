package controller;

import javafx.fxml.Initializable;
import model.Post;
import model.Student;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReadWrite extends MainController implements Initializable {

    private ArrayList<Student> StudentArray;
    private HashMap<String, Post> PostMap;
    private int[] counter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initializeData(ArrayList studentArray, HashMap postMap, int[] counter) {
        this.counter = counter;
        this.PostMap = postMap;
        this.StudentArray = studentArray;
    }

    // Writing Objects to files
    public void write_all_files() {
        writeObjectToFile("src/data/student.dat", getStudentArray());
        writeObjectToFile("src/data/post.dat", PostMap);
        writeObjectToFile("src/data/counter.dat", counter);
        System.out.println("writting"+ getStudentArray());
    }

    public void writeObjectToFile(String s, Object o) {
        try {
            ObjectOutputStream outC = new ObjectOutputStream(new FileOutputStream(s));
            outC.writeObject(o);
            outC.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "**Given file " + s + " is not found, after editing data please procced to store it in file.");
        } catch (IOException ex) {
            System.out.println("**There might have been problem with the file name " + s);
            ex.printStackTrace();
        }
    }

    // For Reading objects
    @SuppressWarnings("unchecked")
    public void read_all_files() {
        setStudentArray(((ArrayList<Student>) readObjectFromFile("src/data/student.dat", StudentArray)));
        setPostMap(((HashMap<String, Post>) readObjectFromFile("src/data/post.dat", PostMap)));
        setCounter(((int[]) readObjectFromFile("src/data/counter.dat", counter)));
        System.out.println("writting"+ StudentArray);
    }

    public Object readObjectFromFile(String s, Object o) {
        try {
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(s));
            o = inStream.readObject();
            inStream.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("**The provided " + s + " Class in not found please ensure it's name");
        } catch (FileNotFoundException ex) {
            System.out.println("**Given file " + s + " is not found");
        } catch (IOException ex) {
            System.out.println("**There might have been problem with the file name " + s);
        }
        return o;
    }
}
