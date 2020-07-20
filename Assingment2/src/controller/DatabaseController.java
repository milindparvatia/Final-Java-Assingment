package controller;

import hsql_db.AddRow;
import hsql_db.CheckTableExist;
import hsql_db.CreateTable;
import hsql_db.SelectQuery;
import model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DatabaseController extends MainController{

    private final static String DB_NAME = "MAINDATABASE";

    public static void addStudent(Student student) throws Throwable {
        try {
            if(CheckTableExist.check("students", DB_NAME)){
            CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS students (id VARCHAR(4) NOT NULL, PRIMARY KEY (id));", DB_NAME);
            }
            AddRow.addingData("INSERT INTO students VALUES ('"+student.getSid()+"')", DB_NAME);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static @Nullable ArrayList<Student> getStudents() throws Throwable {
        try {
            if(CheckTableExist.check("students", DB_NAME)){
                CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS students (id VARCHAR(4) NOT NULL,PRIMARY KEY (id));", DB_NAME);
            }

            return  SelectQuery.getData("SELECT * FROM PUBLIC.STUDENTS", DB_NAME);
        } catch (Throwable throwable) {
            throwable.printStackTrace();

            CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS students (id VARCHAR(4) NOT NULL,PRIMARY KEY (id));", DB_NAME);
        }
        return null;
    }

    public static void addPosts(@NotNull HashMap<String, Post> newPost) {

        newPost.forEach((k, v) -> {
            if (k.contains(getPrefixEvent())) {
                PostEvent event = (PostEvent) v;

                try {
                    if (CheckTableExist.check("events", DB_NAME)) {
                        CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS events (" +
                                "id VARCHAR(10) NOT NULL, " +
                                "title VARCHAR(50) NOT NULL, " +
                                "description VARCHAR(100) NOT NULL, " +
                                "creatorID VARCHAR(4) NOT NULL, " +
                                "imageURL VARCHAR(50), " +
                                "status BOOLEAN NOT NULL, " +
                                "venue VARCHAR(20) NOT NULL," +
                                "date VARCHAR(10) NOT NULL, " +
                                "capacity FLOAT(10) NOT NULL, " +
                                "attendee_count FLOAT(10) NOT NULL , " +
                                "PRIMARY KEY (id));",
                                DB_NAME);
                    }

                    AddRow.addingData("INSERT INTO events VALUES ('"
                            +event.getId()+"', '"
                            +event.getTitle()+"', '"
                            +event.getDescription()+"', '"
                            +event.getCreatorID().getSid()+"', '"
                            +event.getImageURL()+"', '"
                            +event.getStatusForDB()+"', '"
                                +event.getVenue()+"', "
                                +event.getDate()+", "
                            +event.getCapacity()+", "
                            +event.getAttendee_count()+
                            ")", DB_NAME);

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (k.contains(getPrefixSale())) {
                PostSale sale = (PostSale) v;

                try {
                    if (CheckTableExist.check("sales", DB_NAME)) {
                        CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS sales (" +
                                "id VARCHAR(10) NOT NULL," +
                                "title VARCHAR(50) NOT NULL, " +
                                "description VARCHAR(100) NOT NULL, " +
                                "creatorID VARCHAR(4) NOT NULL, " +
                                "imageURL VARCHAR(50), " +
                                "status BOOLEAN NOT NULL, " +
                                "asking_price FLOAT(10) NOT NULL, " +
                                "minimum_raise FLOAT(10) NOT NULL, " +
                                "highest_offer FLOAT(10) NOT NULL," +
                                "PRIMARY KEY (id));",
                                DB_NAME);
                    }

                    AddRow.addingData("INSERT INTO sales VALUES ('"
                            +sale.getId()+"', '"
                            +sale.getTitle()+"', '"
                            +sale.getDescription()+"', '"
                            +sale.getCreatorID().getSid()+"', '"
                            +sale.getImageURL()+"', '"
                            +sale.getStatusForDB()+"', "
                            +sale.getAsking_price()+", "
                            +sale.getMinimum_raise()+", "
                            +sale.getHighest_offer()+
                            ")", DB_NAME);

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (k.contains(getPrefixJob())) {
                PostJob job = (PostJob) v;

                try {
                    if (CheckTableExist.check("jobs", DB_NAME)) {
                        CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS jobs (" +
                                "id VARCHAR(10) NOT NULL, " +
                                "title VARCHAR(50) NOT NULL, " +
                                "description VARCHAR(100) NOT NULL, " +
                                "creatorID VARCHAR(4) NOT NULL, " +
                                        "imageURL VARCHAR(50), " +
                                "status BOOLEAN NOT NULL, " +
                                "propose_price FLOAT(10) NOT NULL, " +
                                "lowest_offer FLOAT(10) NOT NULL, " +
                                "PRIMARY KEY (id));",
                                DB_NAME);
                    }

                    AddRow.addingData("INSERT INTO jobs VALUES ('"
                            +job.getId()+"', '"
                            +job.getTitle()+"', '"
                            +job.getDescription()+"', '"
                            +job.getCreatorID().getSid()+"', '"
                            +job.getImageURL()+"', '"
                            +job.getStatusForDB()+"', "
                            +job.getPropose_price()+", "
                            +job.getLowest_offer()+
                            ")", DB_NAME);

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                System.out.println("a");
            }
        });
    }

    public static @NotNull HashMap<String, Post> getPosts() throws Throwable {

        HashMap<String, Post> PostMap = new HashMap<>();

        if (CheckTableExist.check("events", DB_NAME)) {
            CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS events (" +
                            "id VARCHAR(10) NOT NULL, " +
                            "title VARCHAR(50) NOT NULL, " +
                            "description VARCHAR(100) NOT NULL, " +
                            "creatorID VARCHAR(4) NOT NULL, " +
                            "imageURL VARCHAR(50), " +
                            "status BOOLEAN NOT NULL, " +
                            "venue VARCHAR(20) NOT NULL," +
                            "date VARCHAR(10) NOT NULL, " +
                            "capacity FLOAT(10) NOT NULL, " +
                            "attendee_count FLOAT(10) NOT NULL , " +
                            "PRIMARY KEY (id));",
                    DB_NAME);
        } else {
            PostMap.putAll(SelectQuery.getData("SELECT * FROM PUBLIC.EVENTS", DB_NAME));
        }
        if (CheckTableExist.check("sales", DB_NAME)) {
            CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS sales (" +
                            "id VARCHAR(10) NOT NULL," +
                            "title VARCHAR(50) NOT NULL, " +
                            "description VARCHAR(100) NOT NULL, " +
                            "creatorID VARCHAR(4) NOT NULL, " +
                            "imageURL VARCHAR(50), " +
                            "status BOOLEAN NOT NULL, " +
                            "asking_price FLOAT(10) NOT NULL, " +
                            "minimum_raise FLOAT(10) NOT NULL, " +
                            "highest_offer FLOAT(10) NOT NULL," +
                            "PRIMARY KEY (id));",
                    DB_NAME);
        } else {
            PostMap.putAll(SelectQuery.getData("SELECT * FROM PUBLIC.SALES", DB_NAME));
        }

        if (CheckTableExist.check("jobs", DB_NAME)) {
            CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS jobs (" +
                            "id VARCHAR(10) NOT NULL, " +
                            "title VARCHAR(50) NOT NULL, " +
                            "description VARCHAR(100) NOT NULL, " +
                            "creatorID VARCHAR(4) NOT NULL, " +
                            "imageURL VARCHAR(50), " +
                            "status BOOLEAN NOT NULL, " +
                            "propose_price FLOAT(10) NOT NULL, " +
                            "lowest_offer FLOAT(10) NOT NULL, " +
                            "PRIMARY KEY (id));",
                    DB_NAME);
        } else {
            PostMap.putAll(SelectQuery.getData("SELECT * FROM PUBLIC.JOBS", DB_NAME));
        }

        return PostMap;
        }

    public static void addCounter(int[] counter) {
        try {
            if(CheckTableExist.check("counter", DB_NAME)){
                CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS counter (ID INTEGER IDENTITY PRIMARY KEY, " +
                        "EVENT INT NOT NULL, " +
                        "SALE INT NOT NULL, " +
                        "JOB INT NOT NULL" +
                        ");", DB_NAME);

                AddRow.addingData("INSERT INTO COUNTER (ID, EVENT, SALE, JOB) VALUES(1, 0, 0, 0);", DB_NAME);
            }
            AddRow.addingData("UPDATE COUNTER SET ID = 1, EVENT = "+counter[0]+", SALE = "+counter[1]+", JOB ="+counter[2]+" WHERE ID=1", DB_NAME);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static int @Nullable [] getCounterArray() {
        try {
            if(CheckTableExist.check("counter", DB_NAME)){
                CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS counter (" +
                        "ID INTEGER IDENTITY PRIMARY KEY, " +
                        "EVENT INT NOT NULL, " +
                        "SALE INT NOT NULL, " +
                        "JOB INT NOT NULL" +
                        ");", DB_NAME);
                AddRow.addingData("INSERT INTO COUNTER (ID, EVENT, SALE, JOB) VALUES(1, 0, 0, 0);", DB_NAME);
                return new int[]{0, 0, 0};
            }

            ArrayList<Integer> counter = SelectQuery.getData("SELECT * FROM PUBLIC.COUNTER", DB_NAME);
            int[] array = {0,0,0};

            for (int i = 0; i< Objects.requireNonNull(counter).size(); i++){
                System.out.println("Counter["+i+"] :"+counter.get(i));
                array[i] = counter.get(i);
            }

            return  array;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public static void updatePostReplies(Reply reply) {
        try {
            if (CheckTableExist.check("REPLIES", DB_NAME)) {
                CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS REPLIES (" +
                                "studentID VARCHAR(4) NOT NULL, " +
                                "value FLOAT(20) NOT NULL, " +
                                "postID VARCHAR(6) NOT NULL, " +
                                "PRIMARY KEY (studentID, postID)" +
                                ");", DB_NAME);
            }
            System.out.println(reply.getPostID().getId()+"---------");
            AddRow.addingData("INSERT INTO REPLIES VALUES ('"+reply.getRespondersId().getSid()+"', "+reply.getValue()+", '"+reply.getPostID().getId()+"')", DB_NAME);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static HashMap<String, Post> getAllReplies(HashMap<String, Post> postMap, ArrayList<Student> studentArray) {
        try {
            if (CheckTableExist.check("REPLIES", DB_NAME)) {
                CreateTable.creatingTable("CREATE TABLE IF NOT EXISTS REPLIES (" +
                        "studentID VARCHAR(4) NOT NULL, " +
                        "value FLOAT(20) NOT NULL, " +
                        "postID VARCHAR(6) NOT NULL, " +
                        "PRIMARY KEY (studentID, postID)" +
                        ");", DB_NAME);
            }

                try {
                    ArrayList<String> obj = SelectQuery.getData("SELECT * FROM PUBLIC.REPLIES", DB_NAME);
                    System.out.println(obj);

                    String studentID;
                    Float value;
                    String postID;

                    for(int i=0; i< obj.size(); i++) {
                        System.out.println(obj.get(i));

                        Student student = null;
                        studentID = obj.get(i);
                        value = Float.parseFloat(obj.get(i+1));
                        postID = obj.get(i+2);
                        i = i+2;

                        for(int j=0; j< studentArray.size(); j++) {
                            if(studentArray.get(j).getSid().equals(studentID)) student = studentArray.get(j);
                        }

                        assert student != null;
                        System.out.println(student.getSid()+"obj ----**----");

                        Reply reply = new Reply(postMap.get(postID), value, student);
                        System.out.println(postMap.get(postID));


                        if (postMap.get(postID).handleReply(reply)){
                            System.out.println("workinggggggg");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return postMap;
    }

    public static void uploadImage(String postID,String imageURL) {
        try {
            String tableName = "";

            if(postID.contains("EVE")){
                tableName = "EVENTS";
            } else if(postID.contains("SAL")){
                tableName = "SALES";
            } else  if (postID.contains("JOB")){
                tableName = "JOBS";
            }

            AddRow.addingData("UPDATE "+tableName+" SET IMAGEURL = '"+imageURL+"' WHERE ID = '"+postID+"';", DB_NAME);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void updatePost(String updateQuery) {
        try {
            AddRow.addingData(updateQuery, DB_NAME);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
