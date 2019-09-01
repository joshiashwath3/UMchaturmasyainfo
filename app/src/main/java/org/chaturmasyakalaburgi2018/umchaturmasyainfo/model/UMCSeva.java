package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

/**
 * Created by 19SIMBLS LLP on 22,July,2018
 *
 * @Author Ashwath Joshi
 */
public class UMCSeva {

    private int sevaID;
    private String sevaName;
    private String sevaAmount;
    private String sevaURL;

    public UMCSeva() {
    }

    public UMCSeva(int sevaID, String sevaName, String sevaAmount, String sevaURL) {
        this.sevaID = sevaID;
        this.sevaName = sevaName;
        this.sevaAmount = sevaAmount;
        this.sevaURL = sevaURL;
    }

    public int getSevaID() {
        return sevaID;
    }

    public void setSevaID(int sevaID) {
        this.sevaID = sevaID;
    }

    public String getSevaName() {
        return sevaName;
    }

    public void setSevaName(String sevaName) {
        this.sevaName = sevaName;
    }

    public String getSevaAmount() {
        return sevaAmount;
    }

    public void setSevaAmount(String sevaAmount) {
        this.sevaAmount = sevaAmount;
    }

    public String getSevaURL() {
        return sevaURL;
    }

    public void setSevaURL(String sevaURL) {
        this.sevaURL = sevaURL;
    }
}
