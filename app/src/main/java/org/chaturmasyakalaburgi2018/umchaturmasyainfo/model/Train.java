package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

/**
 * Created by 19SIMBLS LLP on 22,July,2018
 *
 * @Author Ashwath Joshi
 */
public class Train {

    private String trainNameNo;
    private String trainFromTo;
    private String trainDepartsDay;
    private String trainTimeFromToDur;

    public Train() {
    }

    public Train(String trainNameNo, String trainFromTo, String trainDepartsDay, String trainTimeFromToDur) {
        this.trainNameNo = trainNameNo;
        this.trainFromTo = trainFromTo;
        this.trainDepartsDay = trainDepartsDay;
        this.trainTimeFromToDur = trainTimeFromToDur;
    }

    public String getTrainNameNo() {
        return trainNameNo;
    }

    public void setTrainNameNo(String trainNameNo) {
        this.trainNameNo = trainNameNo;
    }

    public String getTrainFromTo() {
        return trainFromTo;
    }

    public void setTrainFromTo(String trainFromTo) {
        this.trainFromTo = trainFromTo;
    }

    public String getTrainDepartsDay() {
        return trainDepartsDay;
    }

    public void setTrainDepartsDay(String trainDepartsDay) {
        this.trainDepartsDay = trainDepartsDay;
    }

    public String getTrainTimeFromToDur() {
        return trainTimeFromToDur;
    }

    public void setTrainTimeFromToDur(String trainTimeFromToDur) {
        this.trainTimeFromToDur = trainTimeFromToDur;
    }
}
