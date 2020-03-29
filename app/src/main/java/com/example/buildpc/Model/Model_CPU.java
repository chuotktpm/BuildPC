package com.example.buildpc.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Model_CPU implements Parcelable {
    public int id;
    public String imageCPU;
    public String socket;
    public String model;
    public String brand;
    public String genaration;
    public String description;
    public int price;

    public Model_CPU(int id, String imageCPU, String socket, String model, String brand, String genaration, String description, int price) {
        this.id = id;
        this.imageCPU = imageCPU;
        this.socket = socket;
        this.model = model;
        this.brand = brand;
        this.genaration = genaration;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
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

    public String getGenaration() {
        return genaration;
    }

    public void setGenaration(String genaration) {
        this.genaration = genaration;
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

    public String getImageCPU() {
        return imageCPU;
    }

    public void setImageCPU(String imageCPU) {
        this.imageCPU = imageCPU;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getSocket());
        dest.writeString(getModel());
        dest.writeString(getBrand());
        dest.writeString(getGenaration());
        dest.writeString(getDescription());
        dest.writeString(getImageCPU());
        dest.writeInt(getPrice());
    }

    public Model_CPU(Parcel in) {
        this.imageCPU = in.readString();
        this.socket = in.readString();
        this.model = in.readString();
        this.brand =in.readString();
        this.genaration = in.readString();
        this.description = in.readString();
        this.price = in.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Model_CPU createFromParcel(Parcel in) {
            return new Model_CPU(in);
        }

        public Model_CPU[] newArray(int size) {
            return new Model_CPU[size];
        }
    };
}
