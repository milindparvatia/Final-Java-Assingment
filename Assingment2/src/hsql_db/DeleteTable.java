package hsql_db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class DeleteTable {
    public DeleteTable() {
    }

    public static void main(String[] args) throws SQLException, Throwable {
        String DB_NAME = "testDB";
        String var2 = "STUDENT";

        try {
            Throwable var3 = null;
            Object var4 = null;

            try {
                Connection con = ConnectionTest.getConnection("mainDATA");

                try {

                    try (Statement stmt = con.createStatement()) {
                        int result = stmt.executeUpdate("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
                        if (result == 0) {
                            System.out.println("Table STUDENT has been deleted successfully");
                        } else {
                            System.out.println("Table STUDENT was not deleted");
                        }
                    }
                } catch (Throwable var21) {
                    if (var3 == null) {
                        var3 = var21;
                    } else if (var3 != var21) {
                        var3.addSuppressed(var21);
                    }

                    if (con != null) {
                        con.close();
                    }

                    throw var3;
                }

                if (con != null) {
                    con.close();
                }
            } catch (Throwable var22) {
                if (var3 == null) {
                    var3 = var22;
                } else if (var3 != var22) {
                    var3.addSuppressed(var22);
                }

                throw var3;
            }
        } catch (Exception var23) {
            System.out.println(var23.getMessage());
        }

    }
}
