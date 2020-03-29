package com.example.buildpc.Model;

public class Model_PC {
    private String imageBuild;
    private String name;
    private String description;
    private String type;
    private int PC_ID;
    private int cpu_ID;
    private int ram_ID;
    private int main_ID;
    private int psu_ID;
    private int vga_ID;
    private int hdd_ID;
    private int ssd_ID;
    private int case_ID;
    private int price;

    public Model_PC(String imageBuild, String description, String name, String type, int PC_ID, int cpu_ID, int ram_ID, int main_ID, int psu_ID, int vga_ID, int hdd_ID , int ssd_ID, int case_ID, int price) {
        this.imageBuild = imageBuild;
        this.name = name;
        this.description = description;
        this.type = type;
        this.PC_ID = PC_ID;
        this.cpu_ID = cpu_ID;
        this.ram_ID = ram_ID;
        this.main_ID = main_ID;
        this.psu_ID = psu_ID;
        this.vga_ID = vga_ID;
        this.hdd_ID = hdd_ID;
        this.ssd_ID = ssd_ID;
        this.case_ID = case_ID;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageBuild() {
        return imageBuild;
    }

    public void setImageBuild(String imageBuild) {
        this.imageBuild = imageBuild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPC_ID() {
        return PC_ID;
    }

    public void setPC_ID(int PC_ID) {
        this.PC_ID = PC_ID;
    }

    public int getCpu_ID() {
        return cpu_ID;
    }

    public void setCpu_ID(int cpu_ID) {
        this.cpu_ID = cpu_ID;
    }

    public int getRam_ID() {
        return ram_ID;
    }

    public void setRam_ID(int ram_ID) {
        this.ram_ID = ram_ID;
    }

    public int getMain_ID() {
        return main_ID;
    }

    public void setMain_ID(int main_ID) {
        this.main_ID = main_ID;
    }

    public int getPsu_ID() {
        return psu_ID;
    }

    public void setPsu_ID(int psu_ID) {
        this.psu_ID = psu_ID;
    }

    public int getVga_ID() {
        return vga_ID;
    }

    public void setVga_ID(int vga_ID) {
        this.vga_ID = vga_ID;
    }

    public int getHdd_ID() {
        return hdd_ID;
    }

    public void setHdd_ID(int hdd_ID) {
        this.hdd_ID = hdd_ID;
    }

    public int getSsd_ID() {
        return ssd_ID;
    }

    public void setSsd_ID(int ssd_ID) {
        this.ssd_ID = ssd_ID;
    }

    public int getCase_ID() {
        return case_ID;
    }

    public void setCase_ID(int case_ID) {
        this.case_ID = case_ID;
    }
}
