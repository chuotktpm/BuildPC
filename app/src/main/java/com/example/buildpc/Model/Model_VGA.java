package com.example.buildpc.Model;

public class Model_VGA {
    public String imageVGA;
    public String name;
    public String size;
    public String model;
    public String brand;
    public String GPU;
    public String Description;
    public int price;
    public int vga_id;

    public Model_VGA (String imageVGA, String name, String size, String model, String brand, String GPU, String Description, int price, int vga_id){
        this.imageVGA = imageVGA;
        this.name = name;
        this.size = size;
        this.model = model;
        this.brand = brand;
        this.GPU = GPU;
        this.Description = Description;
        this.price = price;
        this.vga_id = vga_id;
    }

    public String getImageVGA() {
        return imageVGA;
    }

    public void setImageVGA(String imageVGA) {
        this.imageVGA = imageVGA;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getVga_id() {
        return vga_id;
    }

    public void setVga_id(int vga_id) {
        this.vga_id = vga_id;
    }
}

