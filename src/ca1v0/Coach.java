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
public class Coach extends Bus {
    private boolean WiFi;
    private boolean Toilet;

  public Coach(int id, String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid, boolean wf, boolean t) {
        super(id,r,mk,md,c,engn,pd,sd,gid);
        this.WiFi = wf;
        this.Toilet = t;
    }
   public Coach(String r, String mk, String md, int c, Double engn, Date pd, Date sd, int gid, boolean wf, boolean t) {
        this(-1,r,mk,md,c,engn,pd,sd,gid,wf,t);
    }
  
    
    
    /**
     * @return the WiFi
     */
    public boolean isWiFi() {
        return WiFi;
    }

    /**
     * @param WiFi the WiFi to set
     */
    public void setWiFi(boolean WiFi) {
        this.WiFi = WiFi;
    }

    /**
     * @return the Toilet
     */
    public boolean isToilet() {
        return Toilet;
    }

    /**
     * @param Toilet the Toilet to set
     */
    public void setToilet(boolean Toilet) {
        this.Toilet = Toilet;
    }
}
