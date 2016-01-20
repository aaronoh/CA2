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
public class Vehicles {

    private String make;
    private String model;
    private int capacity;
    private Double engineSize;
    private Date purchaseDate;
    private Date serviceDate;

   public Vehicles(String mk, String md, int c, Double engn, Date pd, Date sd) {
       //constructor used by add bus as the user does not enter the bus id (AI in the database)
        
        this.make = mk;
        this.model = md;
        this.capacity = c;
        this.engineSize = engn;
        this.purchaseDate = pd;
        this.serviceDate = sd;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the engineSize
     */
    public Double getEngineSize() {
        return engineSize;
    }

    /**
     * @param engineSize the engineSize to set
     */
    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the serviceDate
     */
    public Date getServiceDate() {
        return serviceDate;
    }

    /**
     * @param serviceDate the serviceDate to set
     */
    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }
    


    public String rowToString() {
        //method used to present data stored in array list (populated by db) in view method 
        return  "\n***********************************\n"
                + "\n*  Make: " + this.getMake()
                + "\n*  Model: " + this.getModel()
                + "\n*  Capacity: " + this.getCapacity()
                + "\n*  Engine Size: " + this.getEngineSize()
                + "\n*  Purchase Date: " + this.getPurchaseDate()
                + "\n*  Service Date: " + this.getServiceDate()
                +"\n***********************************\n";
    }

}
