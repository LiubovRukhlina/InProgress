package com.InProgress;
import java.io.*;
import  java.lang.String;
import java.util.*;

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

    public void explode(Asteroid A) { }

    public void sublime(Asteroid A) { }

    //</editor-fold>

    //<editor-fold desc="Getters and Setters">

    public String getResourceType() { return resourceType; }

    //</editor-fold>

}
