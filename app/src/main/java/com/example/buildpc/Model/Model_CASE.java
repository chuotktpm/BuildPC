package com.example.buildpc.Model;

public class Model_CASE {
    public String imageCASE;
    public String name;
    public String type;
    public String brand;
    public String description;
    public int price, ID;

    public Model_CASE(String imageCASE, String name, String type, String brand, String description, int price, int ID) {
        this.imageCASE = imageCASE;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImageCASE() {
        return imageCASE;
    }

    public void setImageCASE(String imageCASE) {
        this.imageCASE = imageCASE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
