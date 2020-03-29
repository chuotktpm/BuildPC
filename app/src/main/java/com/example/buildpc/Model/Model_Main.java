package com.example.buildpc.Model;

public class Model_Main {
    public String imageMain;
    public String socket;
    public String brand;
    public String model;
    public String description;
    public String memoryType;
    public int price, ID;

    public Model_Main(String imageMain, String socket, String brand, String model, String description, int price, int ID, String memoryType) {
        this.imageMain = imageMain;
        this.socket = socket;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.price = price;
        this.ID = ID;
        this.memoryType = memoryType;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImageMain() {
        return imageMain;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
