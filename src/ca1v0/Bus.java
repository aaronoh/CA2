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

    private int busId;
    private String reg;
    private int garageId;

    public Bus(String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid) {
        super(mk, md, c, engn, pd, sd);
        //constructor used by add bus as the user does not enter the bus id (AI in the database)
        this.reg = r;
        this.garageId = gid;
    }

    public Bus(int id, String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid) {
        //constructor used by array list (which is populated by db on initial run) for view as viewing id is needed 
        super(mk, md, c, engn, pd, sd);
        this.busId = id;
        this.reg = r;
        this.garageId = gid;
    }

    /**
     * @return the busId
     */
    public int getbusId() {
        return busId;
    }

    /**
     * @param reg the reg to set
     */
    public void setBusId(int busId) {
        this.busId = busId;
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

    public String rowToString() {
        //method used to present data stored in array list (populated by db) in view method 
        return "\n***********************************\n"
                + "*  Bus ID: " + this.getbusId()
                + "\n*  Registration: " + this.getReg()
                + "\n*  Make: " + this.getMake()
                + "\n*  Model: " + this.getModel()
                + "\n*  Capacity: " + this.getCapacity()
                + "\n*  Engine Size: " + this.getEngineSize()
                + "\n*  Purchase Date: " + this.getPurchaseDate()
                + "\n*  Service Date: " + this.getServiceDate()
                + "\n*  Garage ID: " + this.getgarageId()
                + "\n***********************************\n";

    }
}
