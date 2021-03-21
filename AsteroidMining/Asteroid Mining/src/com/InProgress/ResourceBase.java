package com.InProgress;
import java.io.*;
import  java.lang.String;
import java.util.*;

public class ResourceBase {

    protected String resourceType;


    /**
     * constructor for ResourceBase
     * @param resourceType Name of the resource
     */
    public ResourceBase(String resourceType) { this.resourceType = resourceType; }


    /**
     * Returns the name of the Resource type
     *
     * @return Name of the Resource type
     */
    public String getResourceType() { return resourceType; }


}
