package com.example.demo;

public class IncidentText {
    private int id;
    private String description;
    private String steps_to_reproduce;
    private String additional_information;

    IncidentText(){

    }

    IncidentText(int id,
                 String description,
                 String steps_to_reproduce,
                 String additional_information
                 ){
        this.description = description;
        this.steps_to_reproduce = steps_to_reproduce;
        this.additional_information = additional_information;
        this.id = id;
    }

    public int getId(){
        return id;

    }
    public void setId(int id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps_to_reproduce() {
        return steps_to_reproduce;
    }

    public void setSteps_to_reproduce(String steps_to_reproduce) {
        this.steps_to_reproduce = steps_to_reproduce;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }
}
