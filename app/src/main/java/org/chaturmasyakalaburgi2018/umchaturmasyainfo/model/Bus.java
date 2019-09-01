package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

/**
 * Created by 19SIMBLS LLP on 22,July,2018
 *
 * @Author Ashwath Joshi
 */
public class Bus {
    private String busNameNo;
    private String busACNONAC;
    private String busFromTo;
    private String busTimeFromToDur;

    public String getBusNameNo() {
        return busNameNo;
    }

    public void setBusNameNo(String busNameNo) {
        this.busNameNo = busNameNo;
    }

    public String getBusACNONAC() {
        return busACNONAC;
    }

    public void setBusACNONAC(String busACNONAC) {
        this.busACNONAC = busACNONAC;
    }

    public String getBusFromTo() {
        return busFromTo;
    }

    public void setBusFromTo(String busFromTo) {
        this.busFromTo = busFromTo;
    }

    public String getBusTimeFromToDur() {
        return busTimeFromToDur;
    }

    public void setBusTimeFromToDur(String busTimeFromToDur) {
        this.busTimeFromToDur = busTimeFromToDur;
    }

}
