package CA1V0;

/**
 *
 * @author Aaron
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FerryTableGateway {

    //defining database table/column names
    private static final String TABLE_NAME = "ferry";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MAKE = "make";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_ENGINESIZE = "engineSize";
    private static final String COLUMN_PURCHASEDATE = "purchaseDate";
    private static final String COLUMN_SERVICEDATE = "serviceDate";
    private static final String COLUMN_CABINS = "cabins";
    private static final String COLUMN_CREWMEMBERS = "crewMembers";
    private static final String COLUMN_NAME = "name";

    //db connection
    private Connection newConnection;

    public FerryTableGateway(Connection connection) {
        newConnection = connection;
    }

    //Method used to view data from Ferry table, data is taken from DB and stored in array list
    public ArrayList<Ferry> getFerries() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmnt;               // the java object used to execute SQL query
        ResultSet rs;                   // the ResultSet representing the result of the SQL query
        ArrayList<Ferry> ferries;   // the ArrayList containing the Ferry object

        int id;
        String make;
        String model;
        int capacity;
        Double engineSize;
        Date purchaseDate = null;
        Date serviceDate = null;
        int cabins;
        int crewMembers;
        String name;

        Ferry ferry;                   // a Ferry object created from a row in the result of the query

        // execute an SQL SELECT statement to get a ResultSet representing the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmnt = this.newConnection.createStatement();
        rs = stmnt.executeQuery(query);

        ferries = new ArrayList<Ferry>();
        //while the table has rows, retrieve the data from the columns listed below
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            capacity = rs.getInt(COLUMN_CAPACITY);
            engineSize = rs.getDouble(COLUMN_ENGINESIZE);
            purchaseDate = rs.getDate(COLUMN_PURCHASEDATE);
            serviceDate = rs.getDate(COLUMN_SERVICEDATE);
            cabins = rs.getInt(COLUMN_CABINS);
            crewMembers = rs.getInt(COLUMN_CREWMEMBERS);
            name = rs.getString(COLUMN_NAME);
            //create a new Ferry object using the data pulled from the db
            ferry = new Ferry(id, make, model, capacity, engineSize, purchaseDate, serviceDate, cabins, crewMembers, name);
            ferries.add(ferry); //add the Ferry object to the array list
        }
        return ferries; //returns arraylist of all Ferryes in table
    }

    public boolean insertFerry(Ferry ferries) throws SQLException {

        String query; // the SQL query to execute
        PreparedStatement stmnt; // the java object used to execute SQL query

        int numRowsAffected;
        int id;
        boolean success = true;

        //  SQL INSERT statement 
        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_MAKE + ", "
                + COLUMN_MODEL + ", "
                + COLUMN_CAPACITY + ", "
                + COLUMN_ENGINESIZE + ", "
                + COLUMN_PURCHASEDATE + ", "
                + COLUMN_SERVICEDATE + ", "
                + COLUMN_CABINS + ","
                + COLUMN_CREWMEMBERS + ", "
                + COLUMN_NAME
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // create a PreparedStatement object to execute the query
        try {
            stmnt = newConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);//connect to db
            stmnt.setString(1, ferries.getMake());//assigns each variable in the Ferry object to a collumn in the db
            stmnt.setString(2, ferries.getModel());
            stmnt.setInt(3, ferries.getCapacity());
            stmnt.setDouble(4, ferries.getEngineSize());
            stmnt.setDate(5, new Date(ferries.getPurchaseDate().getTime()));
            stmnt.setDate(6, new Date(ferries.getServiceDate().getTime()));
            stmnt.setInt(7, ferries.getCabins());
            stmnt.setInt(8, ferries.getCrewMembers());
            stmnt.setString(9, ferries.getName());

            //  execute the query and make sure only one row was inserted 
            numRowsAffected = stmnt.executeUpdate();
            if (numRowsAffected == 1) {
                //Retrieve id assigned to that row and create a Ferry object 
                ResultSet keys = stmnt.getGeneratedKeys();
                keys.next();

                id = keys.getInt(1);

                ferries.setId(id);
            } else {
                System.err.println("No rows were changed");
                success = false;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            success = false;
        }
        return success;
    }
}
