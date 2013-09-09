/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fruct.oss.kareliafishing.models;

import javax.microedition.lcdui.Image;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.13
 * This class describes properties of fish object. 
 */
public class Fish {
    
    private String id;
    private String name;
    private String fishInfo;
    private Image picture;

    public Fish() {
        id = "";
        name = "";
        fishInfo = "";
        picture = null;
    }

    public Fish(String name, String fishInfo, Image picture) {
        this.name = name;
        this.fishInfo = fishInfo;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public String getFishInfo() {
        return fishInfo;
    }

    public Image getPicture() {
        return picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFishInfo(String fishInfo) {
        this.fishInfo = fishInfo;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }
}
