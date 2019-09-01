package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

/**
 * Created by 19SIMBLS LLP on 31,July,2018
 *
 * @Author Ashwath Joshi
 */
public class UMUrls {

    private int urlId;
    private String urlName;
    private String urlUrl;
    private String urldate;

    public UMUrls(int urlId, String urldate, String urlName, String urlUrl) {
        this.urlId = urlId;
        this.urldate = urldate;
        this.urlName = urlName;
        this.urlUrl = urlUrl;

    }

    public UMUrls() {

    }

    public String getUrldate() {
        return urldate;
    }

    public void setUrldate(String urldate) {
        this.urldate = urldate;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlUrl() {
        return urlUrl;
    }

    public void setUrlUrl(String urlUrl) {
        this.urlUrl = urlUrl;
    }
}
