//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hsql_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public ConnectionTest() {
    }

    public static Connection returnConnection(String DB_NAME) throws Throwable {
        Connection con = null;

        try {
            Throwable var2 = null;
            Object var3 = null;

            try {
                con = getConnection(DB_NAME);

                try {
                    System.out.println("Connection to database "+DB_NAME+" created successfully");
                    return con;
                } finally {
                    if (con != null) {
                        con.close();
                    }

                }
            } catch (Throwable var12) {
                if (var2 == null) {
                    var2 = var12;
                } else if (var2 != var12) {
                    var2.addSuppressed(var12);
                }

                throw var2;
            }
        } catch (Exception var13) {
            System.out.println(var13.getMessage());
        }
        return con;
    }

    public static Connection getConnection(String dbName) throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:file:database/" + dbName, "SA", "");
        return con;
    }
}
