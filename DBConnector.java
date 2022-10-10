import java.sql.*;
import java.util.ArrayList;

public class DBConnector {

    public static Connection C;
    private final String URI = "postgres://ldzppgrijsjuon:707715348636d5b29cc22c369af9092097feee3f3f3359e9bb2c06d621ebd549@ec2-35-168-122-84.compute-1.amazonaws.com:5432/dcv40bfvosv88";
    private final String USER = "ldzppgrijsjuon";
    private final String PASS = "707715348636d5b29cc22c369af9092097feee3f3f3359e9bb2c06d621ebd549";

    DBConnector() {
        //C = null;
    }

public Connection getConnection(){
    C = null;
    try{
        C = DriverManager.getConnection(URI, USER, PASS);
        System.out.println("CONNECTION WORKS");
    }
    catch(SQLException e)
    {
        System.out.println(e.getErrorCode());
        System.out.println(e.getMessage());
    }
    return C;
}

public void createPlayer(int id, String firstName, String lastName, String codeName){
    try {
        if (!C.isClosed()) {
            Statement SQLstatement = C.createStatement();

            String insert = "INSERT INTO player VALUES ('" + id + "', '" + firstName + "', '" + lastName + "', '" + codeName + "');";
            SQLstatement.executeUpdate(insert);
            System.out.println("PLAYER INFO INSERTED INTO DB");
        } else {
            System.out.println("No Connection\n");
        }
    } catch (SQLException e) {
        System.out.println("Couldn't insert player queries\n");
        System.out.println(e.getMessage());
    }
}
}