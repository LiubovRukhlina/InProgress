package com.InProgress;

public class ResourceBase {

    protected String resourceType;

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceBase(String resourceType) {
        this.resourceType = resourceType;
    }
}