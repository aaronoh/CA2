/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA1V0;

import java.util.Date;

/**
 *
 * @author Aaron
 */
public class Ferry extends Vehicles {

    private int ferryID;
    private int cabins;
    private int crewMembers;
    private String name;

    public Ferry(String mk, String md, int c, Double engn, Date pd, Date sd, int cb, int cm, String nm) {
        super(mk, md, c, engn, pd, sd);
        //constructor used by add bus as the user does not enter the bus id (AI in the database)
        this.cabins = cb;
        this.crewMembers = cm;
        this.name = nm;
    }

    public Ferry(int fid, String mk, String md, int c, Double engn, Date pd, Date sd, int cb, int cm, String nm) {
        super(mk, md, c, engn, pd, sd);
        //constructor used by add bus as the user does not enter the bus id (AI in the database)
        this.ferryID = fid;
        this.cabins = cb;
        this.crewMembers = cm;
        this.name = nm;
    }
    
     @Override
    public void display()
    {
         
        // call the superclass display for superclass variales
        super.display();
        System.out.printf("%7s %9s %16s %9s\n",
        this.cabins,
        this.crewMembers,
        this.name,
        this.ferryID);
    }

    /**
     * @return the ferryID
     */
    public int getFerryID() {
        return ferryID;
    }

    /**
     * @param ferryID the ferryID to set
     */
    public void setFerryID(int ferryID) {
        this.ferryID = ferryID;
    }

    /**
     * @return the cabins
     */
    public int getCabins() {
        return cabins;
    }

    /**
     * @param cabins the cabins to set
     */
    public void setCabins(int cabins) {
        this.cabins = cabins;
    }

    /**
     * @return the crewMembers
     */
    public int getCrewMembers() {
        return crewMembers;
    }

    /**
     * @param crewMembers the crewMembers to set
     */
    public void setCrewMembers(int crewMembers) {
        this.crewMembers = crewMembers;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String rowToStringFerries() {
        //method used to present data stored in array list (populated by db) in view method 
        return "\n***********************************\n"
                + "*  ferryID: " + this.getFerryID()
                + "\n*  Make: " + this.getMake()
                + "\n*  Model: " + this.getModel()
                + "\n*  Capacity: " + this.getCapacity()
                + "\n*  Engine Size: " + this.getEngineSize()
                + "\n*  Purchase Date: " + this.getPurchaseDate()
                + "\n*  Service Date: " + this.getServiceDate()
                + "\n*  Cabins: " + this.getCabins()
                + "\n*  Crew Members: " + this.getCrewMembers()
                + "\n*  Name: " + this.getName()
                + "\n***********************************\n";

    }
}
