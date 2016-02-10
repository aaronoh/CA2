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

public class BusTableGateway {

    //defining database table/column names
    private static final String TABLE_NAME = "bus";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_REG = "reg";
    private static final String COLUMN_MAKE = "make";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_ENGINESIZE = "engineSize";
    private static final String COLUMN_PURCHASEDATE = "purchaseDate";
    private static final String COLUMN_SERVICEDATE = "serviceDate";
    private static final String COLUMN_GARAGEID = "garageId";

    //db connection
    private Connection newConnection;

    public BusTableGateway(Connection connection) {
        newConnection = connection;
    }

    //Method used to view data from bus table, data is taken from DB and stored in array list
    public ArrayList<Bus> getBuses() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmnt;               // the java object used to execute SQL query
        ResultSet rs;                   // the ResultSet representing the result of the SQL query
        ArrayList<Bus> busses;   // the ArrayList containing the Bus object

        int id;
        String reg;
        String make;
        String model;
        int capacity;
        Double engineSize;
        Date purchaseDate = null;
        Date serviceDate = null;
        int garageId;

        Bus bus;                   // a Bus object created from a row in the result of the query

        // execute an SQL SELECT statement to get a ResultSet representing the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmnt = this.newConnection.createStatement();
        rs = stmnt.executeQuery(query);

        busses = new ArrayList<Bus>();
        //while the table has rows, retrieve the data from the columns listed below
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            reg = rs.getString(COLUMN_REG);
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            capacity = rs.getInt(COLUMN_CAPACITY);
            engineSize = rs.getDouble(COLUMN_ENGINESIZE);
            purchaseDate = rs.getDate(COLUMN_PURCHASEDATE);
            serviceDate = rs.getDate(COLUMN_SERVICEDATE);
            garageId = rs.getInt(COLUMN_GARAGEID);
            //create a new bus object using the data pulled from the db
            bus = new Bus(id, reg, make, model, capacity, engineSize, purchaseDate, serviceDate, garageId);
            busses.add(bus); //add the bus object to the array list
        }
        return busses; //returns arraylist of all buses in table
    }

    public boolean insertBus(Bus buses) throws SQLException {

        String query; // the SQL query to execute
        PreparedStatement stmnt; // the java object used to execute SQL query

        int numRowsAffected;
        int id;
        boolean success = true;

        //  SQL INSERT statement 
        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_REG + ", "
                + COLUMN_MAKE + ", "
                + COLUMN_MODEL + ", "
                + COLUMN_CAPACITY + ", "
                + COLUMN_ENGINESIZE + ", "
                + COLUMN_PURCHASEDATE + ", "
                + COLUMN_SERVICEDATE + ", "
                + COLUMN_GARAGEID
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // create a PreparedStatement object to execute the query
        try {
            stmnt = newConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);//connect to db
            stmnt.setString(1, buses.getReg());//assigns each variable in the bus object to a collumn in the db
            stmnt.setString(2, buses.getMake());
            stmnt.setString(3, buses.getModel());
            stmnt.setInt(4, buses.getCapacity());
            stmnt.setDouble(5, buses.getEngineSize());
            stmnt.setDate(6, new Date(buses.getPurchaseDate().getTime()));
            stmnt.setDate(7, new Date(buses.getServiceDate().getTime()));
            stmnt.setInt(8, buses.getgarageId());

            //  execute the query and make sure only one row was inserted 
            numRowsAffected = stmnt.executeUpdate();
            if (numRowsAffected == 1) {
                //Retrieve id assigned to that row and create a Bus object 
                ResultSet keys = stmnt.getGeneratedKeys();
                keys.next();

                id = keys.getInt(1);

                buses.setId(id);
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

    public boolean deleteBus(int busid) throws SQLException {

        String query; // the SQL query to execute
        PreparedStatement stmnt;  // the java object used to execute SQL query
        int numRowsAffected;
        //SQL Delete statement
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ? ";

        stmnt = newConnection.prepareStatement(query);//connect to db
        stmnt.setInt(1, busid);//assigns the busid to the busid collumn in the db

        numRowsAffected = stmnt.executeUpdate();

        return (numRowsAffected == 1);
    }

    public boolean updateBus(Bus b) throws SQLException {
        String query; // the SQL query to execute
        PreparedStatement stmnt; // the java object used to execute SQL query
        int numRowsAffected = 0;
        //SQL update query
        query = " UPDATE " + TABLE_NAME + " SET "
                + COLUMN_REG + " = ?, "
                + COLUMN_MAKE + " = ?, "
                + COLUMN_MODEL + " = ?, "
                + COLUMN_CAPACITY + " = ?, "
                + COLUMN_ENGINESIZE + " = ?, "
                + COLUMN_PURCHASEDATE + " = ?, "
                + COLUMN_SERVICEDATE + " = ?, "
                + COLUMN_GARAGEID + " = ? "
                + " WHERE " + COLUMN_ID + " = ?";

        stmnt = newConnection.prepareStatement(query);//db connection
        stmnt.setString(1, b.getReg());//assigns each variable in the bus object to a collumn in the db
        stmnt.setString(2, b.getMake());
        stmnt.setString(3, b.getModel());
        stmnt.setInt(4, b.getCapacity());
        stmnt.setDouble(5, b.getEngineSize());
        stmnt.setDate(6, new Date(b.getPurchaseDate().getTime()));
        stmnt.setDate(7, new Date(b.getServiceDate().getTime()));
        stmnt.setInt(8, b.getgarageId());
        stmnt.setInt(9, b.getId());

        numRowsAffected = stmnt.executeUpdate();

        return (numRowsAffected == 1);
    }

}
