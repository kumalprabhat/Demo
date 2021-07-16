
package com.example.demo;

import java.util.HashMap;
import java.util.Map;



public class Category {

    private String name;


    /**
     * No args constructor for use in serialization
     * 
     */
    public Category() {
    }

    /**
     * 
     * @param name
     */
    public Category(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = "Category";
    }



}
