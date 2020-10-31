package com.example.projectiteration1.model;

import java.util.ArrayList;

/**
 * ReportList singleton class models the information
 * about a list of inspection report that can be shared between
 * activities. Data includes arrayList of InspectionReport
 * objects. It supports adding new InspectionReport to the list.
 */
public class ReportsList {
    private ArrayList<InspectionReport> reports = new ArrayList<>();

    /*
        Singleton support
    */
    private ReportsList() {
        // To prevent from instantiating
    }
    private static ReportsList instance;
    public static ReportsList getInstance() {
        if (instance == null) {
            instance = new ReportsList();
        }
        return instance;
    }

    public ArrayList<InspectionReport> getReports() {
        return reports;
    }

    public void add(InspectionReport report) {
        reports.add(report);
    }
}
