/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1v0;

import java.util.Date;

/**
 *
 * @author Aaron
 */
public class Luxury extends Bus {
    private boolean FoodService;
    private boolean Beds;

  public Luxury(int id, String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid, boolean fs, boolean bd) {
        super(id,r,mk,md,c,engn,pd,sd,gid);
        this.FoodService = fs;
        this.Beds = bd;
    }
   public Luxury(String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid, boolean fs, boolean bd) {
        this(-1,r,mk,md,c,engn,pd,sd,gid,fs,bd);
    }

    /**
     * @return the FoodService
     */
    public boolean isFoodService() {
        return FoodService;
    }

    /**
     * @param FoodService the FoodService to set
     */
    public void setFoodService(boolean FoodService) {
        this.FoodService = FoodService;
    }

    /**
     * @return the Beds
     */
    public boolean isBeds() {
        return Beds;
    }

    /**
     * @param Beds the Beds to set
     */
    public void setBeds(boolean Beds) {
        this.Beds = Beds;
    }
  
    

}
