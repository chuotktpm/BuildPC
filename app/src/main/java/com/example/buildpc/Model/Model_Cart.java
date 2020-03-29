package com.example.buildpc.Model;

import java.io.Serializable;

public class Model_Cart implements Serializable {
    private int cpuID, ramID, mainID, vgaID, hddID, ssdID, psuID, caseID;

    public Model_Cart(int cpuID, int ramID, int mainID, int vgaID, int hddID, int ssdID, int psuID, int caseID) {
        this.cpuID = cpuID;
        this.ramID = ramID;
        this.mainID = mainID;
        this.vgaID = vgaID;
        this.hddID = hddID;
        this.ssdID = ssdID;
        this.psuID = psuID;
        this.caseID = caseID;
    }

    public Model_Cart() {
        this.cpuID = 0;
        this.ramID = 0;
        this.mainID = 0;
        this.vgaID = 0;
        this.hddID = 0;
        this.ssdID = 0;
        this.psuID = 0;
        this.caseID = 0;
    }

    public int getCpuID() {
        return cpuID;
    }

    public void setCpuID(int cpuID) {
        this.cpuID = cpuID;
    }

    public int getRamID() {
        return ramID;
    }

    public void setRamID(int ramID) {
        this.ramID = ramID;
    }

    public int getMainID() {
        return mainID;
    }

    public void setMainID(int mainID) {
        this.mainID = mainID;
    }

    public int getVgaID() {
        return vgaID;
    }

    public void setVgaID(int vgaID) {
        this.vgaID = vgaID;
    }

    public int getHddID() {
        return hddID;
    }

    public void setHddID(int hddID) {
        this.hddID = hddID;
    }

    public int getSsdID() {
        return ssdID;
    }

    public void setSsdID(int ssdID) {
        this.ssdID = ssdID;
    }

    public int getPsuID() {
        return psuID;
    }

    public void setPsuID(int psuID) {
        this.psuID = psuID;
    }

    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public void clear(){
        setCpuID(0);
        setRamID(0);
        setMainID(0);
        setVgaID(0);
        setHddID(0);
        setSsdID(0);
        setPsuID(0);
        setCaseID(0);
    }
}
