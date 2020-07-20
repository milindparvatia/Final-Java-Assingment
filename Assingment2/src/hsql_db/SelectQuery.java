//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hsql_db;

import model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectQuery {

    public SelectQuery() {
    }

    public static @Nullable <T> T getData(String query, String DB_NAME) {

        Throwable var3 = null;

        ArrayList<Student> StudentArray = new ArrayList<>();
        HashMap<String, Post> PostMap = new HashMap<>();
        ArrayList<Integer> counter = new ArrayList<>();
        ArrayList<String> replies = new ArrayList<>();

        try {
            Connection con = ConnectionTest.getConnection(DB_NAME);

            try {

                try (Statement stmt = con.createStatement()) {

                    try (ResultSet resultSet = stmt.executeQuery(query)) {

                        if (query.contains("STUDENTS")) {
                            getStudentArray(StudentArray, resultSet);
                            con.close();
                            return (T) StudentArray;
                        } else if (query.contains("EVENTS")) {
                            getEventArray(PostMap, resultSet);
                            con.close();
                            return (T) PostMap;
                        } else if (query.contains("SALES")) {
                            getSaleArray(PostMap, resultSet);
                            con.close();
                            return (T) PostMap;
                        } else if (query.contains("JOBS")) {
                            getJobArray(PostMap, resultSet);
                            con.close();
                            return (T) PostMap;
                        } else if (query.contains("COUNTER")) {
                            getCounterArray(counter, resultSet);
                            con.close();
                            return (T) counter;
                        } else if (query.contains("REPLIES")) {
                            getRepliesArray(replies, resultSet);
                            con.close();
                            return (T) replies;
                        }
                    } catch (Throwable var50) {
                        var3 = var50;

                        con.close();

                        throw var3;
                    }


                } catch (Throwable var51) {
                    if (var3 == null) {
                        var3 = var51;
                    } else if (var3 != var51) {
                        var3.addSuppressed(var51);
                    }

                    throw var3;
                }
            } catch (Exception var52) {
                System.out.println(var52.getMessage());
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    private static void getCounterArray(ArrayList<Integer> counter, @NotNull ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            counter.add(resultSet.getInt("EVENT"));
            counter.add(resultSet.getInt("SALE"));
            counter.add(resultSet.getInt("JOB"));
        }
    }

    private static void getJobArray(HashMap<String, Post> postMap, @NotNull ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString(resultSet.getMetaData().getColumnName(1)));

            postMap.put(resultSet.getString("ID"),
                    new PostJob(
                            resultSet.getString("ID"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            new Student(resultSet.getString("creatorID")),
                            resultSet.getBoolean("status"),
                            resultSet.getString("imageURL"),
                            resultSet.getFloat("propose_price"),
                            resultSet.getFloat("lowest_offer")
                    ));
        }
    }

    private static void getSaleArray(HashMap<String, Post> postMap, @NotNull ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString(resultSet.getMetaData().getColumnName(1)));

            postMap.put(resultSet.getString("ID"),
                    new PostSale(
                            resultSet.getString("ID"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            new Student(resultSet.getString("creatorID")),
                            resultSet.getBoolean("status"),
                            resultSet.getString("imageURL"),
                            resultSet.getFloat("asking_price"),
                            resultSet.getFloat("highest_offer"),
                            resultSet.getFloat("minimum_raise")
                    ));
        }
    }

    private static void getEventArray(HashMap<String, Post> postMap, @NotNull ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString(resultSet.getMetaData().getColumnName(1)));

            postMap.put(resultSet.getString("ID"),
                    new PostEvent(
                            resultSet.getString("ID"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            new Student(resultSet.getString("creatorID")),
                            resultSet.getBoolean("status"),
                            resultSet.getString("imageURL"),
                            resultSet.getString("venue"),
                            resultSet.getString("date"),
                            resultSet.getInt("capacity"),
                            resultSet.getInt("attendee_count")
                    ));
        }
    }

    private static void getStudentArray(ArrayList<Student> studentArray, @NotNull ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("in here "+resultSet.getString("ID"));
            System.out.println(resultSet.getString(resultSet.getMetaData().getColumnName(1)));

            studentArray.add(new Student(resultSet.getString("ID")));
        }
    }

    private static void getRepliesArray(ArrayList<String> replies, @NotNull ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            replies.add(resultSet.getString("studentID"));
            replies.add(resultSet.getString("value"));
            replies.add(resultSet.getString("postID"));
        }
    }
}
