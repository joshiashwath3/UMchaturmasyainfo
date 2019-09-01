package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

/**
 * Created by 19SIMBLS LLP on 03,August,2018
 *
 * @Author Ashwath Joshi
 */
public class Help {

    private int helpID;
    private String helpName;
    private String helpContact;

    public Help(int helpID, String helpName, String helpContact) {
        this.helpID = helpID;
        this.helpName = helpName;
        this.helpContact = helpContact;
    }

    public Help() {

    }

    public int getHelpID() {
        return helpID;
    }

    public void setHelpID(int helpID) {
        this.helpID = helpID;
    }

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

    public String getHelpContact() {
        return helpContact;
    }

    public void setHelpContact(String helpContact) {
        this.helpContact = helpContact;
    }
}
