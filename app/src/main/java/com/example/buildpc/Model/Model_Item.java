package com.example.buildpc.Model;

public class Model_Item {
        public String ImageItem;
        public String NameItem;
        public String Brand;
        public int Price;

    public Model_Item(String imageItem, String nameItem, String brand, int price) {
        ImageItem = imageItem;
        NameItem = nameItem;
        Brand = brand;
        Price = price;
    }

    public String getImageItem() {
        return ImageItem;
    }

    public void setImageItem(String imageItem) {
        ImageItem = imageItem;
    }

    public String getNameItem() {
        return NameItem;
    }

    public void setNameItem(String nameItem) {
        NameItem = nameItem;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
