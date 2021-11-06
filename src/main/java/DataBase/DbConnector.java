package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static String URl = "jdbc:mysql://localhost/program_database";
    private static String USER = "root";
    private static String PASSWORD = "1234";


    public static Connection connect() throws SQLException {
        Connection connection =null;
        try {
            connection = DriverManager.getConnection(URl, USER, PASSWORD);
//            System.out.println("Połączono");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Polaczenie nie powoiodlo sie na poziomie Connectora");
        }
        return connection;
    }
}
