package beatsServer;

/**
 * Created by Tycho on 6-6-2017.
 */
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    private static DAO uniqueInstance=null;
    private static Connection conn = null ;
    private Statement stmt = null;
    private DBConnection dbCon = null;
    private DAO(DBConnection db){ //precondition dbExisis()
        conn = db.getConnection();
        //  System.err.println("The database doesn’t exist....");
    }

    public static synchronized DAO getInstance(DBConnection db) { // apply singleton design pattern for CityDao
        if (uniqueInstance == null)
            uniqueInstance = new DAO(db);
        return uniqueInstance;
    }

    public ArrayList<String> getInfo(String column) { //
        ArrayList<String> cities = new ArrayList<String>();
        try{
            // Execute the query
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM userinfo" );;
            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set
            while( rs.next())
                cities.add(rs.getString(column) ) ;
        }catch( SQLException se ) { se.printStackTrace(); }
        return cities;
    }
}
