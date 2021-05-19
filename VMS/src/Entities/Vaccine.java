package Entities;

import Utils.Constants;

import java.util.Date;

public class Vaccine {

    private String vaccineId;
    private String vaccineName;
    private String assignedTo;
    private Date assignedTime;
    private String mfgDetails;
    private Date expiryDate;

    private volatile static int n = 0;

    private synchronized String nextNum() {
        n++;
        return n + "";
    }

    public Vaccine(String vaccineName, String mfgDetails, Date expiryDate) {
        this.vaccineId = Constants.VACCINE_SEQUENCE + nextNum();
        this.vaccineName = vaccineName;
        this.mfgDetails = mfgDetails;
        this.expiryDate = expiryDate;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Date assignedTime) {
        this.assignedTime = assignedTime;
    }

    public String getMfgDetails() {
        return mfgDetails;
    }

    public void setMfgDetails(String mfgDetails) {
        this.mfgDetails = mfgDetails;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public static int getN() {
        return n;
    }

    public static void setN(int n) {
        Vaccine.n = n;
    }
}
