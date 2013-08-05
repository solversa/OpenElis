package org.bahmni.feed.openelis.feed.domain;

public class LabObject {
    private String externalId;
    private String name;
    private String description;
    private String sysUserId;
    private String category;
    private String status;

    public String getCategory() {
        return category;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public LabObject (){}

    public LabObject(String externalId, String name, String description, String sysUserId, String category, String status) {
        this.externalId = externalId;
        this.name = name;
        this.description = description;
        this.sysUserId = sysUserId;
        this.category = category;
        this.status = status;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
