package org.chaturmasyakalaburgi2018.umchaturmasyainfo.common;

import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by 19SIMBLS LLP on 02,July,2018
 *
 * @Author Ashwath Joshi
 */

public class FolderCreation extends AppCompatActivity {

    public void folder() {

        File rootFolder = new File(Environment.getExternalStorageDirectory() + "/Android/data/org.chaturmasyakalaburgi2018");
        File firstParent = new File(rootFolder + "/images");
        File secondParent = new File(rootFolder + "/dump");
        File childOne = new File(firstParent + "/imgbuc");
        File childTwo = new File(firstParent + "/imgpbk");
        File childOne1 = new File(secondParent + "/imgbuc");
        File childTwo1 = new File(secondParent + "/imgpbk");
        boolean success = true;
        if (!rootFolder.exists()) {
            success = rootFolder.mkdirs();
            if (!firstParent.exists()) {
                success = firstParent.mkdir();
                if (!childOne.exists()) {
                    success = childOne.mkdir();
                }
                if (!childTwo.exists()) {
                    success = childTwo.mkdir();
                }
            }
            if (!secondParent.exists()) {
                success = secondParent.mkdir();
                if (!childOne1.exists()) {
                    success = childOne1.mkdir();
                }
                if (!childTwo1.exists()) {
                    success = childTwo1.mkdir();
                }
            }
        }
        if (!success) {
        } else {
        }
    }

/*    public void tenantImageUploadFolder() {
        File rootFolder = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.simbls/images/imgpbk/");
        File firstParent = new File(rootFolder + "/Tenant_Image/");
        boolean success = true;
        rootFolder.mkdirs();
        if (!firstParent.exists()) {
            success = firstParent.mkdir();
        }

        if (!success) {
        } else {
        }
    }*/
}