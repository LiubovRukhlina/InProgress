package com.InProgress;

import java.io.FileNotFoundException;
import  java.lang.String;


public class ResourceBase {

    //<editor-fold desc="Attributes">

    protected String resourceType;

    //</editor-fold>


    //<editor-fold desc="Constructor">

    /**
     * constructor for ResourceBase
     * @param resourceType Name of the resource
     */
    public ResourceBase(String resourceType) {
        this.resourceType = resourceType; }

    //</editor-fold">

    //<editor-fold desc="Methods">

    /**
     * Virtual method. Implementation is in Uranium class.
     * @param A Asteroid that has this Resource
     */
    protected void explode(Asteroid A)  { }

    /**
     * Virtual method. Implementation is in WaterIce class.
     * @param A Asteroid that has this Resource
     */
    protected void sublime(Asteroid A) { }

    //</editor-fold>

    //<editor-fold desc="Getters and Setters">

    public String getResourceType() { return resourceType; }

    //</editor-fold>

}
