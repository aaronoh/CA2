package CA1V0;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CA1V0 {

    public static void main(String[] args) {

        TestModel model = TestModel.getInstance(); //gets/creates instance of testmodelclass, 

        Bus b = null;//bus object

        Scanner in = new Scanner(System.in);
        int menuOpt;
        //Allowing user to run the programme in a loop while the menuOpt isnt 0 (exit)
        do {
            System.out.println("-----------------------MENU-------------------------");
            System.out.println("1 - View current Bus list");
            System.out.println("2 - View current Ferry list");
            System.out.println("3 - File Input");
            System.out.println("4 - Generate Maintenance Invoices");
            System.out.println("0 - Exit");
            System.out.println("-----------------------------------------------------\n");
            System.out.println("What would you like to do?");
            menuOpt = in.nextInt();

            System.out.println("Menu number chosen " + menuOpt + "\n");

            switch (menuOpt) {
                case 1: {
                    System.out.println("Viewing Buses...\n");
                    viewBuses(model);
                    break;
                }
                case 2: {
                    System.out.println("Viewing Ferries...\n");
                    viewFerries(model);
                    break;
                }

                case 3: {
                    System.out.println("File Input....");
                    readBusFile(in, model);
                    break;
                }

                case 4: {
                    System.out.println("Generating Maintenance Invoices....");
                    //generateMInvoices(model);
                    break;
                }

                case 0: {
                    System.out.println("Exiting....");
                    break;
                }
            }
            //Ends the loop when 0 chosen
        } while (menuOpt != 0);
    }

    private static String getString(Scanner in, String a) {
        System.out.println(a);
        return in.nextLine();
    }

    public static void readBusFile(Scanner kb, TestModel m) {

        System.out.println("Enter file:");
        kb.nextLine();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String inputFileName = kb.nextLine();
        File inputFile = new File(inputFileName);
        try {
            Scanner in = new Scanner(inputFile);
            while (in.hasNextLine()) {

                String line = in.nextLine();
                if (line.equalsIgnoreCase("B")) {
                    createBus(in, m);
                } else if (line.equalsIgnoreCase("F")) {
                    createFerry(in, m);
                }

            }
            in.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot find file");

        }

    }

    public static void createBus(Scanner in, TestModel model) {

        String r = in.nextLine();
        String mk = in.nextLine();
        String md = in.nextLine();
        int c = in.nextInt();
        Double engn = in.nextDouble();
        String x = in.nextLine();
        String pd = in.nextLine();
        String sd = in.nextLine();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date purDate = null;
        Date serviceDate = null;

        try {
            purDate = df.parse(pd);
            serviceDate = df.parse(sd);
        } catch (ParseException ex) {
            Logger.getLogger(CA1V0.class.getName()).log(Level.SEVERE, null, ex);
        }
        int gid = in.nextInt();

        Bus b = new Bus(r, mk, md, c, engn, purDate, serviceDate, gid);
        model.addBus(b);

    }

    public static void createFerry(Scanner in, TestModel model) {

        String mk = in.nextLine();
        String md = in.nextLine();
        int c = in.nextInt();
        Double engn = in.nextDouble();
        String x = in.nextLine();
        String pd = in.nextLine();
        String sd = in.nextLine();

        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        Date purDate = null;
        Date serviceDate = null;

        try {
            purDate = df.parse(pd);
            serviceDate = df.parse(sd);
        } catch (ParseException ex) {
            Logger.getLogger(CA1V0.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cbns = in.nextInt();
        int crw = in.nextInt();
        x = in.nextLine();
        String nme = in.nextLine();
        Ferry f = new Ferry(mk, md, c, engn, purDate, serviceDate, cbns, crw, nme);
        model.addFerry(f);

    }

    private static void viewFerries(TestModel m) {
        List<Ferry> vehicles = m.getFerries();
        System.out.println();
        if (vehicles.isEmpty()) {
            System.out.println("There are no ferries in the database");//if ferrys arraylist is empty display this 
        } else {
            System.out.printf("%10s %11s %12s %12s %15s %15s %15s %9s %15s %10s\n\n",
                    "Id", "Make", "Model", "Capacity", "Engine Size", "Purchase Date", "Service Date", "Cabins", "Crew Members", "Name");
            for (Vehicles vh : vehicles) {
                vh.display();

            }
        }
        System.out.println();
    }

    private static void viewBuses(TestModel m) {
        List<Bus> vehicles = m.getBuses();
        System.out.println();
        if (vehicles.isEmpty()) {
            System.out.println("There are no buses in the database");

        } else {
            System.out.printf("%11s %11s %12s %12s %15s %14s %15s %9s %16s\n\n",
                    "Id", "Make", "Model", "Capacity", "Engine Size", "Purchase Date", "Service Date", "Reg", "Garaged ID");
            for (Vehicles vh : vehicles) {
                vh.display();

            }
        }
        System.out.println();
    }
}
