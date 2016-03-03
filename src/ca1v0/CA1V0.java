package CA1V0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Integer.parseInt;

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
            System.out.println("3 - View Vehicles");
            System.out.println("4 - File Input");
            System.out.println("5 - Generate Maintenance Invoices");
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
                    System.out.println("Viewing Vehicles...\n");
                    System.out.println("Buses:\n");
                    viewBuses(model);
                    System.out.println("Ferries:\n");
                    viewFerries(model);
                    break;
                }

                case 4: {
                    System.out.println("File Input....(File Name: in.txt)");
                    readFile(in, model);
                    break;
                }

                case 5: {

                    System.out.println("Generating Maintenance Invoices....");
                    //views vehicles first so that the id can be retrieved by the user
                    System.out.println("Buses:\n");
                    viewBuses(model);
                    System.out.println("Ferries:\n");
                    viewFerries(model);
                   //user inputs id
                    int id;
                    System.out.println("Enter ID of the required vehicle");
                    id = in.nextInt();
                    //bus & ferry objects created, entered id searched for 
                    Bus b1;
                    Ferry f1;
                    b1 = model.findBusByid(id);
                    f1 = model.findFerryByid(id);
                    //if the bus or ferry object is not empty (if they are empty the id didnt exist in either table) & if its less than 999
                    // ids in my database are 0+ for buses and 1000+ for ferries
                    if (b1 != null || f1 != null && id < 999) {

                        generateMInvoices(in, b1, model);
                        System.out.println("Bus Invoice has been saved to the specicified text file in the root of the  project folder");
                    } 
                    
                    else if (b1 != null || f1 != null && id > 999) {

                        generateMInvoices(in, f1, model);
                        System.out.println("Ferry Invoice has been saved to the specicified text file in the root of the  project folder");
                    } else {
                        System.out.println("Vehicle not found");
                    }
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

    public static void readFile(Scanner kb, TestModel m) {

        System.out.println("Enter file:");
        kb.nextLine();//blank read to fix issues with scanner skipping input
        String inputFileName = kb.nextLine();
        File inputFile = new File(inputFileName);
        try {
            Scanner in = new Scanner(inputFile);
            //while there are lines left in the file read them, if it reaches a B create bus, if it reaches a F create ferry. 
            while (in.hasNextLine()) {

                String line = in.nextLine();
                if (line.equalsIgnoreCase("B")) {
                    createBus(in, m);
                } else if (line.equalsIgnoreCase("F")) {
                    createFerry(in, m);
                }

            }
            System.out.println("Entries added successfully");
            in.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file");

        }

    }

    public static void createBus(Scanner in, TestModel model) {
        //storing lines in text file as variables
        String r = in.nextLine();
        String mk = in.nextLine();
        String md = in.nextLine();
        int c = in.nextInt();
        Double engn = in.nextDouble();
        String x = in.nextLine();
        String pd = in.nextLine();
        String sd = in.nextLine();
        

        //parsing dates
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        Date purDate = null;
        Date serviceDate = null;

        try {
            purDate = df.parse(pd);
            serviceDate = df.parse(sd);
        } catch (ParseException ex) {
            Logger.getLogger(CA1V0.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        int gid = in.nextInt();
        
        //create bus with variables read from file
        Bus b = new Bus(r, mk, md, c, engn, purDate, serviceDate, gid);
        model.addBus(b);

    }

    public static void createFerry(Scanner in, TestModel model) {
        //storing lines in text file as variables
        String mk = in.nextLine();
        String md = in.nextLine();
        int c = in.nextInt();
        Double engn = in.nextDouble();
        String x = in.nextLine();
        String pd = in.nextLine();
        String sd = in.nextLine();

        //parsing dates
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
        
        //create Ferry with variables read from file
        Ferry f = new Ferry(mk, md, c, engn, purDate, serviceDate, cbns, crw, nme);
        model.addFerry(f);

    }

    private static void viewFerries(TestModel m) {
        //retrieve list of ferries from model
        List<Ferry> vehicles = m.getFerries();
        System.out.println();
        //if the list is emppty - error message
        if (vehicles.isEmpty()) {
            System.out.println("There are no ferries in the database");//if ferrys arraylist is empty display this 
        } else {
            //display in 'table' form, formatted using %10s, %15s etc.
            System.out.printf("%10s %11s %12s %12s %15s %15s %15s %9s %15s %10s\n\n",
                    "Id", "Make", "Model", "Capacity", "Engine Size", "Purchase Date", "Service Date", "Cabins", "Crew Members", "Name");
            //for every vehicle in the array, invoke the display mehtod
            for (Vehicles vh : vehicles) {
                vh.display();

            }
        }
    }

    private static void viewBuses(TestModel m) {
        //retrieve list of buses from model
        List<Bus> vehicles = m.getBuses();
        System.out.println();
        if (vehicles.isEmpty()) {
            System.out.println("There are no buses in the database");

        } else {
            //display in 'table' form, formatted using %10s, %15s etc.
            System.out.printf("%11s %11s %12s %12s %15s %14s %15s %9s %16s\n\n",
                    "Id", "Make", "Model", "Capacity", "Engine Size", "Purchase Date", "Service Date", "Reg", "Garaged ID");
            //for every vehicle in the array, invoke the display mehtod
            for (Vehicles vh : vehicles) {
                vh.display();//polymorphism

            }
        }
    }

    private static void generateMInvoices(Scanner in, Vehicles vehicle, TestModel m) {
        PrintWriter out = null;
        String outFileName;

        try {
            //create a new text file with the specified name
            System.out.println("Enter the name of the file you wish to output to (no file extension)");
            outFileName = in.next();
            out = new PrintWriter(new File(outFileName + ".txt"));
            //write the printInvoice interface to the file
            out.println(vehicle.printInvoice());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CA1V0.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                //close print writerS
                out.close();
            }
        }

    }
}
