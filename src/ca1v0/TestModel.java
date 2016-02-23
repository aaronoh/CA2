package CA1V0;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestModel {

    private static TestModel instance = null;

    public static synchronized TestModel getInstance() { //if an instance of the TestModel is not present, one is created
        if (instance == null) {
            instance = new TestModel();
        }
        return instance;
    }

    private ArrayList<Bus> buses;//array list that stores the bus objects
    private ArrayList<Ferry> ferries;
    BusTableGateway busTableGateway;
    FerryTableGateway ferryTableGateway;

    private TestModel() {

        busTableGateway = new BusTableGateway(DBConnection.getInstance().getDbConnection());
        try {
            this.buses = busTableGateway.getBuses();
        } catch (SQLException ex) {
            Logger.getLogger(TestModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        ferryTableGateway = new FerryTableGateway(DBConnection.getInstance().getDbConnection());
        try {
            this.ferries = ferryTableGateway.getFerries();
        } catch (SQLException ex) {
            Logger.getLogger(TestModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Bus> getBuses() {
        return this.buses;
    }

    public ArrayList<Ferry> getFerries() {
        return this.ferries;
    }

    public void addBus(Bus b) {
        this.buses.add(b);
        try {
            busTableGateway.insertBus(b);//inserts bus into db
        } catch (SQLException e) {
            System.err.println("ERROR " + e.getMessage());//informs user if SQL error occurs
        }
    }

    public void addFerry(Ferry f) {
        this.ferries.add(f);
        try {
            ferryTableGateway.insertFerry(f);//inserts bus into db
        } catch (SQLException e) {
            System.err.println("ERROR " + e.getMessage());//informs user if SQL error occurs
        }
    }

    public Bus findBusByid(int id) {
        Bus b = null;
        int i = 0;
        boolean found = false;
        while (i < this.buses.size() && !found) {//while i is less than the number of items in the array list and i hasn't been found
            b = this.buses.get(i);
            if (b.getId() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            b = null;
        }
        return b;
    }
    
     public Ferry findFerryByid(int id) {
        Ferry f = null;
        int i = 0;
        boolean found = false;
        while (i < this.ferries.size() && !found) {//while i is less than the number of items in the array list and i hasn't been found
            f = this.ferries.get(i);
            if (f.getId() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            f = null;
        }
        return f;
    }

     


}
