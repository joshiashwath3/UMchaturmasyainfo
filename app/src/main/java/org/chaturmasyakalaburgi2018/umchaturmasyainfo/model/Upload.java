package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

/**
 * Created by 19SIMBLS LLP on 31,July,2018
 *
 * @Author Ashwath Joshi
 */
public class Upload {

    private String mName;
    private String mImageUrl;
    private String subTitle;
    private String date;
    private String discreption;



    public Upload() {
    }

    public Upload(String name, String imageUrl,String date,String subTitle,String discreption) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        this.mName = name;
        this.mImageUrl = imageUrl;
        this.date = date;
        this.subTitle =subTitle;
        this.discreption =discreption;
    }

    public String getDiscreption() {
        return discreption;
    }

    public void setDiscreption(String discreption) {
        this.discreption = discreption;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
