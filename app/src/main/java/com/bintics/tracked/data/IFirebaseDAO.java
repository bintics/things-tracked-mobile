package com.bintics.tracked.data;

import com.bintics.tracked.bean.Device;

/**
 * Created by andru-fb on 20/03/17.
 */

public interface IFirebaseDAO {
    final String URL_DATA_ROOT = "clients/locates/locatecdmx/devices";

    public void pushDevice(Device device);
    public void updateLocationDevice(String uid, double latitude,double longitude);
}
