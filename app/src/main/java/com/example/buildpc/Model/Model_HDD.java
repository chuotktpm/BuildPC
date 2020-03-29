package com.example.buildpc.Model;

public class Model_HDD {

    public String imageHDD;
    public String name;
    public String size;
    public String brand;
    public String description;
    public int price, ID;

    public Model_HDD(String imageHDD, String name, String size, String brand, String description, int price, int ID) {
        this.imageHDD = imageHDD;
        this.name = name;
        this.size = size;
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

    public String getImageHDD() {
        return imageHDD;
    }

    public void setImageHDD(String imageHDD) {
        this.imageHDD = imageHDD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
