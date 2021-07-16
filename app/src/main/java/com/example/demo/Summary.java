
package com.example.demo;

import java.util.HashMap;
import java.util.Map;



public class Summary {

    private String summary;
    private String description;
    private Category category;
    private Project project;


    /**
     * No args constructor for use in serialization
     * 
     */
    public Summary() {
    }

    /**
     * 
     * @param summary
     * @param description
     * @param project
     * @param category
     */
    public Summary(String summary, String description, Category category, Project project) {
        super();
        this.summary = summary;
        this.description = description;
        this.category = category;
        this.project = project;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


}
