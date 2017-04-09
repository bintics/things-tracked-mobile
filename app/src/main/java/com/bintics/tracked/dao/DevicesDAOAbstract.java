package com.bintics.tracked.data;

import com.bintics.tracked.bean.Coord;
import com.bintics.tracked.bean.Device;

import java.util.List;

/**
 * Created by federico on 08/04/2017.
 */

public class DevicesDAOAbstract implements IDevicesDAO {

    // final String URL_DATA_ROOT = "clients/pgj/locates/locatecdmx/devices/device1";
    final String URL_DATA_ROOT = "clients/{client}/locates/{locateService}/devices/{deviceId}";

    @Override
    public void saveDevice(Device device) {

    }

    @Override
    public void updateLocation(Coord coord) {

    }

    @Override
    public List<Device> getAllDevices() {
        return null;
    }

    @Override
    public Device getDevice(String deviceName) {
        return null;
    }
}
