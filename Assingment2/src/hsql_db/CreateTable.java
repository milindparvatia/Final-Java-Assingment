//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hsql_db;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {
    public CreateTable() {
    }

    public static void creatingTable(String query, String DB_NAME) throws Throwable {

        try {
            Throwable var3 = null;
            Object var4 = null;

            try {
                Connection con = ConnectionTest.getConnection(DB_NAME);

                try {
                    Statement stmt = con.createStatement();

                    try {
                        int result = stmt.executeUpdate(query);
                        if (result == 0) {
                            System.out.println("Table has been created successfully");
                        } else {
                            System.out.println("Table is not created");
                        }
                    } finally {
                        if (stmt != null) {
                            stmt.close();
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
