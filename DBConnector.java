import java.sql.*;
import java.util.ArrayList;

public class DBConnector {

    static final String JDBC_DRIVER = "org.postgresql.Driver";

    //Database 1: team6-db
    static final String URL = "jdbc:postgresql://ec2-35-168-122-84.compute-1.amazonaws.com:5432/dcv40bfvosv88";
    static final String user = "ldzppgrijsjuon";
    static final String password = "707715348636d5b29cc22c369af9092097feee3f3f3359e9bb2c06d621ebd549";

    Connection conn;
    Statement stm;
    

    DBConnector() {
        //Connection conn = null;
        // Statement stm = null;
    }

public Connection getConnection(){
    try{
        System.out.println("Registering JDBC Driver...");
        Class.forName(JDBC_DRIVER);
        //DriverManager.registerDriver(new org.postgresql.Driver());
        System.out.println("Successfully registered JDBC Driver");

        System.out.println("Trying to connect to database...");
        conn = DriverManager.getConnection(URL, user, password);
        System.out.println("Successfully connected to database");
    }
    catch(Exception e){
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return conn;
}

public void clearTable(){
    try{
        if(!conn.isClosed()){
            Statement stm = conn.createStatement();
            //String ClearDB = "DELETE FROM player WHERE last_name IS NULL;";
            String ClearDB = "DELETE FROM player;";
            stm.executeUpdate(ClearDB);
            //System.out.println("TABLE HAS BEEN CLEARED");
        }
        else{
            System.out.println("There is no connection, failed to send createPlayer Query");
        }
    }
    catch(Exception e){
        System.out.println("Failed to send clearTable Query");
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
}

public void createPlayer(int id, String firstName, String lastName, String codeName, String color){
    try{
        if(!conn.isClosed()){
            //Create SQL Query
            Statement SQLstatement = conn.createStatement();
            String InsertToDB = "INSERT INTO player VALUES ('" + id + "','" + firstName + "','" + lastName + "','" + codeName + "','" + color + "');";
            SQLstatement.executeUpdate(InsertToDB);
            //System.out.println("PLAYER INFO INSERTED INTO DB");
        }
        else{
            System.out.println("There is no connection, failed to send createPlayer Query");
        }
    }
    catch(SQLException e )
    {
        System.out.println("Failed to send createPlayer Query");
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
}

public ArrayList<Integer> pullIDs(String color){
    ArrayList<Integer> idList = new ArrayList<Integer>();
    try{
        if(!conn.isClosed()){
            Statement SQLstatement = conn.createStatement();
            String PullFromDB = "SELECT id from player WHERE color = " + "'" + color + "'" + ";";

            ResultSet rs = SQLstatement.executeQuery(PullFromDB);
                while(rs.next())
                {
                    int DB_id = rs.getInt("id");
                    idList.add(DB_id);
                }
        }
        else{
            System.out.println("There is no connection, failed to send createPlayer Query");
        }
    }
    catch(SQLException e )
    {
        System.out.println("PULL FAILED");
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        return null;
    }
    return idList;
}

public ArrayList<String> pullPlayer(String color){
    ArrayList<String> codenameList = new ArrayList<String>();
    try{
        if(!conn.isClosed()){
            Statement SQLstatement = conn.createStatement();
            String PullFromDB = "SELECT codename from player WHERE color = " + "'" + color + "'" + ";";

            ResultSet rs = SQLstatement.executeQuery(PullFromDB);
                while(rs.next())
                {
                    String DBcodename = rs.getString("codename");
                    codenameList.add(DBcodename);
                }
        }
        else{
            System.out.println("There is no connection, failed to send createPlayer Query");
        }
    }
    catch(SQLException e )
    {
        System.out.println("PULL FAILED");
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        return null;
    }
    return codenameList;
}
}