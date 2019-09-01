package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

import java.util.ArrayList;

/**
 * Created by 19SIMBLS LLP on 22,July,2018
 *
 * @Author Ashwath Joshi
 */
public class UMToReach {

    private String umBusTrain;
    private ArrayList<CityName> cityNames;

    public String getUmBusTrain() {
        return umBusTrain;
    }

    public void setUmBusTrain(String umBusTrain) {
        this.umBusTrain = umBusTrain;
    }

    public ArrayList<CityName> getCityNames() {
        return cityNames;
    }

    public void setCityNames(ArrayList<CityName> cityNames) {
        this.cityNames = cityNames;
    }
}
