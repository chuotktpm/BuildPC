package com.example.buildpc.Model;

public class Model_SSD {
    public String  imageSSD;
    public String name;
    public String size;
    public String port;
    public String brand;
    public String description;
    public int price, ID;


    public Model_SSD(String imageSSD, String name, String size, String port, String brand, String description, int price, int ID) {
        this.imageSSD = imageSSD;
        this.name = name;
        this.size = size;
        this.port = port;
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

    public String  getImageSSD() {
        return imageSSD;
    }

    public void setImageSSD(String  imageSSD) {
        this.imageSSD = imageSSD;
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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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
