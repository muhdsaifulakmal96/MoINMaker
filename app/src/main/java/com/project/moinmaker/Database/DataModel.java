package com.project.moinmaker.Database;

public class DataModel {

    private int id;
    private String amount;
    private String category;
    private String description;
    private String date;
    private String image;
    private String spinner;

    //Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCategory(String category){this.category = category; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getSpinner() {
        return spinner;
    }

    //Constructors
    public DataModel (int id, String amount,String category, String description, String date, String image, String spinner) {
        this.id = id;
        this.amount = amount;
        this.category=category;
        this.description = description;
        this.date=date;
        this.image = image;
        this.spinner=spinner;

    }
    //getter methods
    public int getId(){
        return id;
    }
    public String getAmount(){
        return amount;
    }
    public String getCategory(){return category; }
    public String getDescription(){
        return description;
    }

    public String getImage(){
        return image;
    }
}
