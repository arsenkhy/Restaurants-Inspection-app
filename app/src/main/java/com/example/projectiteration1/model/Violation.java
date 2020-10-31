package com.example.projectiteration1.model;

public class Violation {
    int violationID;
    String seriousness;
    String description;
    String reappearance;

    public int getViolationID() {
        return violationID;
    }

    public void setViolationID(int violationID) {
        this.violationID = violationID;
    }

    public String getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(String seriousness) {
        this.seriousness = seriousness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReappearance() {
        return reappearance;
    }

    public void setReappearance(String reappearance) {
        this.reappearance = reappearance;
    }

    // For debugging purposes
    @Override
    public String toString() {
        return "Violation{" +
                "violationID=" + violationID +
                ", seriousness='" + seriousness + '\'' +
                ", description='" + description + '\'' +
                ", reappearance='" + reappearance + '\'' +
                '}';
    }
}
