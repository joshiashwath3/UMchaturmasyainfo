package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

import java.util.ArrayList;

/**
 * Created by 19SIMBLS LLP on 22,July,2018
 *
 * @Author Ashwath Joshi
 */
public class CityName {

    private String cityNameTrain;
    /*private String cityNameBus;*/
    private ArrayList<Train> trainArrayList;
    private ArrayList<Bus> busArrayList;

    public String getcityNameTrain() {
        return cityNameTrain;
    }

    public void setcityNameTrain(String cityNameTrain) {
        this.cityNameTrain = cityNameTrain;
    }

/*    public String getCityNameBus() {
        return cityNameBus;
    }

    public void setCityNameBus(String cityNameBus) {
        this.cityNameBus = cityNameBus;
    }*/

    public ArrayList<Train> getTrainArrayList() {
        return trainArrayList;
    }

    public void setTrainArrayList(ArrayList<Train> trainArrayList) {
        this.trainArrayList = trainArrayList;
    }

    public ArrayList<Bus> getBusArrayList() {
        return busArrayList;
    }

    public void setBusArrayList(ArrayList<Bus> busArrayList) {
        this.busArrayList = busArrayList;
    }

    @Override
    public String toString() {
        return cityNameTrain;
    }
}
