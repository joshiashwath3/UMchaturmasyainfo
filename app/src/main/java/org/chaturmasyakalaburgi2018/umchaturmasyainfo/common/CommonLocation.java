package org.chaturmasyakalaburgi2018.umchaturmasyainfo.common;

import android.app.Activity;
import android.location.Location;

import com.google.android.gms.location.LocationListener;

/**
 * Created by 19SIMBLS LLP on 30,July,2018
 *
 * @Author Ashwath Joshi
 */
public class CommonLocation extends Activity implements LocationListener {

    public static final double busStandLat = 17.326235;
    public static final double busStandLong = 76.817496;
    public static final String busStand = "Central Bus Stand";
    public static final double railwayStationLat = 17.314924;
    public static final double railwayStationLong = 76.824636;
    public static final String railwayStation = "Gulbarga Railway Station";
    public static final double umLat = 17.337016;
    public static final double umLong = 76.823830;
    public static final String um = "Rukmini Pandurang Temple UM";
    public static final double nvCollegeLat = 17.331145;
    public static final double nvCollegeLong = 76.828053;
    public static final String nvCollege = "N.V.College";
    public static final double jewargiCrossLat = 17.324371;
    public static final double jewargiCrossLong = 76.820230;
    public static final String jewargiCross = "Jewargi Cross";
    public static final double ramMandirCircleLat = 17.301045;
    public static final double ramMandirCircleLong = 76.816747;
    public static final String ramMandirCircle = "Ram Mandir Circle";
    public static double currentLat;
    public static double currentLong;
    public static final String current = "Current Location";
    @Override
    public void onLocationChanged(Location location) {
        currentLat = location.getLongitude();
        currentLong = location.getLatitude();
    }
}
