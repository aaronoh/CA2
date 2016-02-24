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
public class Bus extends Vehicles {

    private String reg;
    private int garageId;

//    public Bus(String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid) {
//        super(mk, md, c, engn, pd, sd);
//        //constructor used by add bus as the user does not enter the bus id (AI in the database)
//        this.reg = r;
//        this.garageId = gid;
//    }
    public Bus(String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid) {
        this(-1, r, mk, md, c, engn, pd, sd, gid);
    }

    public Bus(int id, String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid) {
        //constructor used by array list (which is populated by db on initial run) for view as viewing id is needed 
        super(id, mk, md, c, engn, pd, sd);
        this.reg = r;
        this.garageId = gid;
    }

    //  overrides the display() in the vehicles superclass
    @Override
    public void display() {

        // call the superclass display for superclass variales
        super.display();
        System.out.printf("%14s %8s\n",
                this.reg,
                this.garageId);
    }

    /**
     * @return the reg
     */
    public String getReg() {
        return reg;
    }

    /**
     * @param reg the reg to set
     */
    public void setReg(String reg) {
        this.reg = reg;
    }

    public int getgarageId() {
        return garageId;
    }

    /**
     * @param reg the reg to set
     */
    public void setgarageId(int garageId) {
        this.garageId = garageId;
    }
    
    @Override
    public String printInvoice()
    {
        return super.displayInvoiceDetails() + "\nMainteneance cost for this Bus : Euro 750";
    }
}
