package com.bintics.tracked.dao;

import com.bintics.tracked.bean.Coord;
import com.bintics.tracked.bean.Device;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by federico on 08/04/2017.
 */

public abstract class DevicesDAOAbstract implements IDevicesDAO {

    // final String URL_DATA_ROOT = "clients/pgj/locates/locatecdmx/devices/device1";
    private final String URL_DATA_ROOT;
    private DatabaseReference reference;

    public DevicesDAOAbstract(String clientName, String locationName) {
        String url = "clients/{client}/locates/{locateService}/devices";
        URL_DATA_ROOT = url
                .replace("{client}",clientName)
                .replace("{locateService}",locationName);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference(URL_DATA_ROOT);
    }

    @Override
    public DatabaseReference getReference() {
        return reference;
    }

    @Override
    public String getRootStorage() {
        return URL_DATA_ROOT;
    }
}
