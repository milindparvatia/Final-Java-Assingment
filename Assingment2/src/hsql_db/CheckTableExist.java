//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hsql_db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class CheckTableExist {
    public CheckTableExist() {
    }

    public static boolean check(String query, String DB_NAME) throws Throwable {
        try {
            Throwable var3;

            try {

                try (Connection con = ConnectionTest.getConnection(DB_NAME)) {
                    DatabaseMetaData dbm = con.getMetaData();
                    ResultSet tables = dbm.getTables(null, null, query.toUpperCase(), null);
                    if (tables != null) {
                        if (tables.next()) {
                            System.out.println("Table "+query+" exists.");
                            tables.close();
                            return false;
                        } else {
                            System.out.println("Table "+query+" does not exist.");
                            tables.close();
                            return true;
                        }
                    } else {
                        System.out.println("Problem with retrieving database metadata");
                    }
                }
            } catch (Throwable var15) {
                var3 = var15;
                throw var3;
            }
        } catch (Exception var16) {
            System.out.println(var16.getMessage());
        }

        return false;
    }
}
