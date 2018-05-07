package id.ac.unsyiah.android.mixfood;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by denny on 29/04/18.
 */

class InternetConnection {
    static boolean isOnline(Object systemService) {
        ConnectivityManager cm = (ConnectivityManager) systemService;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }else {
            return false;
        }
    }
}
