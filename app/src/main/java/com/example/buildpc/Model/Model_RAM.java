package com.example.buildpc.Model;

public class Model_RAM {

    public String imageRAM;
    public String size;
    public String brand;
    public String bus;
    public String name;
    public String type;
    public String description;
    public int price, ID;

    public Model_RAM(String imageRAM, String size, String brand, String bus, String name, String type, int price, String description, int ID) {
        this.imageRAM = imageRAM;
        this.size = size;
        this.brand = brand;
        this.bus = bus;
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageRAM() {
        return imageRAM;
    }

    public void setImageRAM(String imageRAM) {
        this.imageRAM = imageRAM;
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

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String description) {
        this.name = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
