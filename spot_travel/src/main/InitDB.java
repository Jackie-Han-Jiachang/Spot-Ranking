import java.sql.Connection;
import java.sql.DriverManager;

public class InitDB {

    /*
    Source: https://www.h2database.com/html/tutorial.html#tutorial_starting_h2_console
    Connecting to a Database using JDBC, bacuse the database is not created yet, we can use the file path to create a new database.
        */
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(
            "jdbc:h2:file:/Users/jackyhan/Java/h2/data/attractionDB", "sa", "");
        conn.close();
        System.out.println("Connnected");
    }
}