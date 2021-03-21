package com.InProgress;
import java.io.*;
import  java.lang.String;
import java.util.*;

public class ResourceBase {
    /**
     * Indiactes the type of resource.
     * reference to all the resources.
     */
    protected String resourceType;

    public ResourceBase(String resourceType) {
        /**
         * constructor for ResourceBase
         */
        this.resourceType = resourceType;
    }

    public void setResourceType(String resourceType) {
        /**
         * setter of the resource type.
         */
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        /**
         * getter of the resource type.
         */
        return resourceType;
    }


}