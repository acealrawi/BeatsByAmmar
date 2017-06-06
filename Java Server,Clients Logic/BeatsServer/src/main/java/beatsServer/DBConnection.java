package beatsServer;

/**
 * Created by Tycho on 6-6-2017.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection{
    private static DBConnection uniqueInstance=null;
    private static Connection con = null ;
    private DBConnection(){
        if(!dbExists())
        {
            System.err.println("the database doesn’t exist....") ;
        }
    }

    public static synchronized DBConnection getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DBConnection();
        }
        return uniqueInstance;
    }

    private Boolean dbExists() {
        Boolean exists = false ;
        Boolean fileloaded = false;
        try {
            Properties props = new Properties();
            try {
                FileInputStream in = new FileInputStream("~/home/ali/IdeaProjects/mysqlweek7/src/database.properties");
                props.load(in);
                in.close();
                fileloaded = true;
            }
            catch (IOException ioex) {
                System.out.println("IO Exception + " + ioex.getMessage());
                fileloaded = false;
            }
            if (fileloaded) {
                String drivers = props.getProperty("jdbc.drivers");
                if (drivers != null) System.setProperty("jdbc.drivers", drivers);
                String url = props.getProperty("jdbc.url");
                String username = props.getProperty("jdbc.username");
                String password = props.getProperty("jdbc.password");
                con = DriverManager.getConnection(url, username,password);
                exists = true;
            }
        }
        catch (SQLException ex) {
        }
        return(exists) ;
    }

    public void close() {
        try {
            //close connection
            con.close();
            uniqueInstance=null;
            con=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }
}
