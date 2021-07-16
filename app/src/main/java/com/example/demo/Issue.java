package com.example.demo;

public class Issue {

    public int project_id;
    public String severity;
    public String reproducibility;
    public String summary;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getReproducibility() {
        return reproducibility;
    }

    public void setReproducibility(String reproducibility) {
        this.reproducibility = reproducibility;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Issue(int project_id, String severity, String reproducibility, String summary) {
        this.project_id = project_id;
        this.severity = severity;
        this.reproducibility = reproducibility;
        this.summary = summary;


    }
}
