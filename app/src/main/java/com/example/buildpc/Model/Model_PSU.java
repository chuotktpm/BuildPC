package com.example.buildpc.Model;

public class Model_PSU {
    public String imagePSU;
    public String name;
    public int size;
    public String brand;
    public String efficiency_rating;
    public String description;
    public int price, ID;

    public Model_PSU(String imagePSU, String name, int size, String brand, String efficiency_rating, String description, int price, int ID) {
        this.imagePSU = imagePSU;
        this.name = name;
        this.size = size;
        this.brand = brand;
        this.efficiency_rating = efficiency_rating;
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

    public String getImagePSU() {
        return imagePSU;
    }

    public void setImagePSU(String imagePSU) {
        this.imagePSU = imagePSU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEfficiency_rating() {
        return efficiency_rating;
    }

    public void setEfficiency_rating(String efficiency_rating) {
        this.efficiency_rating = efficiency_rating;
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
