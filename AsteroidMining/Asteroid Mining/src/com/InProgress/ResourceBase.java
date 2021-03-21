package com.InProgress;
import java.io.*;
import  java.lang.String;
import java.util.*;

public class ResourceBase {
    /**
     * Indicates the type of resource.
     * reference to all the resources.
     */
    protected String resourceType;

    /**
     * constructor for ResourceBase
     * @param resourceType Name of the resource
     */
    public ResourceBase(String resourceType) {

        this.resourceType = resourceType;
    }

    /**
     * @ash
     * I don't think we need a setter for this.
     * The resource type will never be changed form the outside.
     * from Yves
     */
    public void setResourceType(String resourceType) {

        this.resourceType = resourceType;
    }

    /**
     * Returns the name of the Resource type
     *
     * @return Name of the Resource type
     */
    public String getResourceType() {

        return resourceType;
    }


}