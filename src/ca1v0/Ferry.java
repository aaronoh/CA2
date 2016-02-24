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

    private int id;
    private int cabins;
    private int crewMembers;
    private String name;

//    public Ferry(String mk, String md, int c, Double engn, Date pd, Date sd, int cb, int cm, String nm) {
//        super(mk, md, c, engn, pd, sd);
//        //constructor used by add bus as the user does not enter the bus id (AI in the database)
//        this.cabins = cb;
//        this.crewMembers = cm;
//        this.name = nm;
//    }
    public Ferry(int id, String mk, String md, int c, Double engn, Date pd, Date sd, int cb, int cm, String nm) {
        super(id, mk, md, c, engn, pd, sd);
        //constructor used by add bus as the user does not enter the bus id (AI in the database)
        this.cabins = cb;
        this.crewMembers = cm;
        this.name = nm;
    }

    public Ferry(String mk, String md, int c, Double engn, Date pd, Date sd, int cb, int cm, String nm) {
        this(-1, mk, md, c, engn, pd, sd, cb, cm, nm);
    }

    @Override
    public void display() {

        // call the superclass display for superclass variales
        super.display();
        System.out.printf("%7s %12s %16s\n",
                this.cabins,
                this.crewMembers,
                this.name);
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

    @Override
    public String printInvoice() {
                
        return super.displayInvoiceDetails() + "\nMainteneance cost for this Ferry : Euro 1200";
    }
}
