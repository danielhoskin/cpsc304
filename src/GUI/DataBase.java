/**
 * Created by AddisonSasko on 2016-11-09.
 */
package GUI;
import java.sql.*;
import java.util.*;
// for SimpleDateFormat
import java.text.*;


public class DataBase {


    public String connectionString = "jdbc:oracle:thin:@localhost:1522:ug";
    public String username = "ora_v4n0b";
    public String password = "a52589132";


    public void makeDataBase() throws SQLException {
        Connection con = DriverManager.getConnection(connectionString, username, password);
    }
}
