//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hsql_db;

import java.sql.Connection;
import java.sql.Statement;

public class DeleteRow {
    public DeleteRow() {
    }

    public static void main(String[] args) throws Throwable {
        String DB_NAME = "testDB";
        String var2 = "STUDENT";

        try {
            Throwable var3 = null;
            Object var4 = null;

            try {
                Connection con = ConnectionTest.getConnection("testDB");

                try {
                    Statement stmt = con.createStatement();

                    try {
                        String query = "DELETE FROM STUDENT WHERE first_name LIKE 'Tom'";
                        int result = stmt.executeUpdate(query);
                        System.out.println("Delete from table STUDENT executed successfully");
                        System.out.println(result + " row(s) affected");
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }

                    }
                } catch (Throwable var22) {
                    if (var3 == null) {
                        var3 = var22;
                    } else if (var3 != var22) {
                        var3.addSuppressed(var22);
                    }

                    if (con != null) {
                        con.close();
                    }

                    throw var3;
                }

                if (con != null) {
                    con.close();
                }
            } catch (Throwable var23) {
                if (var3 == null) {
                    var3 = var23;
                } else if (var3 != var23) {
                    var3.addSuppressed(var23);
                }

                throw var3;
            }
        } catch (Exception var24) {
            System.out.println(var24.getMessage());
        }

    }
}
