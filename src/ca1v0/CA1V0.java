package CA1V0;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CA1V0 {

    public static void main(String[] args) {

        TestModel model = TestModel.getInstance(); //gets/creates instance of testmodelclass, 

        Bus b = null;//bus object

        /*List<Bus> buses = model.getBuses();
        
         for (Bus bus : model.getBuses()) {
         System.out.println(bus.toString());
         }*/
        Scanner in = new Scanner(System.in);
        int menuOpt;
        //Allowing user to run the programme in a loop while the menuOpt isnt 5 (exit)
        do {
            System.out.println("-----------------------MENU-------------------------");
            System.out.println("1 - Enter Bus");
            System.out.println("2 - View current Bus list");
            System.out.println("3 - Delete from Bus list");
            System.out.println("4 - Update an item in bus list");
            System.out.println("5 - Exit");
            System.out.println("-----------------------------------------------------\n");
            System.out.println("What would you like to do?");
            menuOpt = in.nextInt();

            System.out.println("Menu number chosen " + menuOpt + "\n");

            switch (menuOpt) {
                case 1: {
                    System.out.println("Creating bus...\n");
                    b = readBus(in);//user input method
                    model.addBus(b);//adds bus object to array list 
                    BusTableGateway busTbGateway = new BusTableGateway(DBConnection.getInstance().getDbConnection());
                    try {
                        busTbGateway.insertBus(b);//inserts bus into db
                    } catch (SQLException e) {
                        System.err.println("ERROR " + e.getMessage());//informs user if SQL error occurs
                    }
                    break;
                }
                case 2: {
                    System.out.println("Viewing bus...\n");
                    viewBuses(model);
                    break;
                }
                case 3: {
                    System.out.println("Deleting bus from list...\n");
                    deleteBus(in, model);

                    break;
                }
                case 4: {

                    System.out.println("Updating....");
                    editBus(in, model);

                    break;
                }

                case 5: {
                    System.out.println("Exiting....");
                    break;
                }
            }
            //Ends the loop when 5
        } while (menuOpt != 5);
    }

    private static String getString(Scanner in, String a) {
        System.out.println(a);
        return in.nextLine();
    }

    private static Bus readBus(Scanner in) {
        int busId;
        String reg;
        String make;
        String model;
        int capacity;
        Double engineSize;
        Date purchaseDate = null;
        Date serviceDate = null;
        int garageId;

        //User input for adding bus to bus object
        
        try {
            System.out.println("Enter purchase date (YYYY-MM-DD): ");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            purchaseDate = df.parse(in.next());

            System.out.println("Enter service date (YYYY-MM-DD): ");
            serviceDate = df.parse(in.next());
        } catch (ParseException e) {
            System.err.println("ERROR invalid date format (yyyy-mm-dd)");
            readBus(in); // loop back to the beginning of the method if error in date format
        }

        System.out.println("Enter Bus Reg: ");
        reg = in.next();

        System.out.println("Enter Bus Make: ");
        make = in.next();

        System.out.println("Enter Bus Model: ");
        model = in.next();

        System.out.println("Enter capacity: ");
        capacity = in.nextInt();

        System.out.println("Enter Engine Size: ");
        engineSize = in.nextDouble();

        System.out.println("Enter Garage ID: ");
        garageId = in.nextInt();

        Bus b
                = new Bus(reg, make, model,
                        capacity, engineSize, purchaseDate, serviceDate, garageId); //add all user input to a new bus object, b

        return b;
    }

    private static void viewBuses(TestModel model) {

        for (Bus b : model.getBuses()) {
            System.out.println(b.rowToString());//display data from array list (populated by db)  
        }
    }

    private static void deleteBus(Scanner in, TestModel m) {
        BusTableGateway busTbGateway = new BusTableGateway(DBConnection.getInstance().getDbConnection());//db connection
        System.out.print("Enter the ID of the bus to delete:");
        int busId = in.nextInt();
        Bus b;

        b = m.findBusBybusId(busId);//invokes the findBusById method to identify the bus object being deleted

        try {
            if (busTbGateway.deleteBus(b.getbusId())) {
                System.out.println("Bus Deleted Successfully");
                m.removeBus(b);
            } else {
                System.out.println("Bus not found");
            }
        } catch (SQLException e) {
            System.err.println("ERROR " + e.getMessage());
        }

    }

    private static void editBusData(Scanner in, Bus b) {
        int capacity;
        Double engineSize;
        int garageId;
        String sdupdate = "", rupdate = "", mkupdate = "", mdupdate = "", cpupdate = "", esupdate = "", gidupdate = "";
        //sets the update variabls to the current values
        sdupdate = getString(in, "");
        sdupdate = getString(in, "Enter service date: [" + b.getServiceDate() + "]:");
        rupdate = getString(in, "Enter bus reg: [" + b.getReg() + "]:");
        mkupdate = getString(in, "Enter bus make: [" + b.getMake() + "]:");
        mdupdate = getString(in, "Enter model: [" + b.getModel() + "]:");
        cpupdate = getString(in, "Enter capacity: [" + b.getCapacity() + "]:");
        esupdate = getString(in, "Enter Engine Size: [" + b.getEngineSize() + "]:");
        gidupdate = getString(in, "Enter Garage ID: [" + b.getgarageId() + "]:");
        if (sdupdate.length() != 0) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String newsdate = sdupdate;
            
            try {

                Date date = df.parse(newsdate);
                b.setServiceDate(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (rupdate.length() != 0) {//if the update variable has been changed set its value as the value of the corresponding variable in the bus object (e.g reg)
            b.setReg(rupdate);
        }
        if (mkupdate.length() != 0) {//if the update variable has been changed set its value as the value of the corresponding variable in the bus object
            b.setMake(mkupdate);
        }
        if (mdupdate.length() != 0) {//if the update variable has been changed set its value as the value of the corresponding variable in the bus object
            b.setModel(mdupdate);
        }
        if (cpupdate.length() != 0) {//if the update variable has been changed set its value as the value of the corresponding variable in the bus object
            capacity = Integer.parseInt(cpupdate);
            b.setCapacity(capacity);

        }

        if (esupdate.length() != 0) {//if the update variable has been changed set its value as the value of the corresponding variable in the bus object
            engineSize = Double.parseDouble(esupdate);
            b.setEngineSize(engineSize);

        }

        if (gidupdate.length() != 0) {//if the update variable has been changed set its value as the value of the corresponding variable in the bus object
            garageId = Integer.parseInt(gidupdate);
            b.setgarageId(garageId);
        }

    }

    private static void editBus(Scanner in, TestModel m) {

        int busId;
        System.out.print("Enter ID of the bus you wish to edit");
        busId = in.nextInt();
        Bus b;
        b = m.findBusBybusId(busId);//invokes the findBusById method to identify the bus object being edited

        if (b != null) {//if the bus object b has a value invoke the editBusData method
            editBusData(in, b);

            if (m.updateBus(b)) {//if the bus object b is passed into the updateBus method in the model class
                System.out.println("Bus successfully updated");
            } else {
                System.out.println("Update was not successfull");
            }
        } else {
            System.out.println("Bus could not be found");
        }
    }

}
