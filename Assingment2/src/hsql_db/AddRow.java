//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hsql_db;

import java.sql.Connection;
import java.sql.Statement;

public class AddRow {
    public AddRow() {
    }

    public static void addingData(String query, String DB_NAME) throws Throwable {

        Connection con = ConnectionTest.getConnection(DB_NAME);

        Statement stmt = con.createStatement();

                    try {

                        int result = stmt.executeUpdate(query);
                        con.commit();
                        System.out.println("Insert into table query executed successfully");
                        System.out.println(result + " row(s) affected");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }

                    }
    }
}
