//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hsql_db;

import java.sql.Connection;
import java.sql.Statement;

public class UpdateQuery {
    public UpdateQuery() {
    }

    public static void UpdateQuery(String query, String DB_NAME) throws Throwable {

        try {
            Throwable var3 = null;

            try {
                Connection con = ConnectionTest.getConnection(DB_NAME);

                try {
                    Statement stmt = con.createStatement();

                    try {
                        int result = stmt.executeUpdate(query);
                        System.out.println("Update table Post executed successfully");
                        System.out.println(result + " row(s) affected");
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }

                    }
                } catch (Throwable var22) {
                    var3 = var22;

                    if (con != null) {
                        con.close();
                    }

                    throw var3;
                }

                con.close();
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
