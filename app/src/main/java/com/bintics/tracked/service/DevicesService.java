package com.bintics.tracked.service;

import com.bintics.tracked.bean.Coord;
import com.bintics.tracked.bean.Device;
import com.bintics.tracked.dao.DevicesDAO;
import com.bintics.tracked.dao.IDevicesDAO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by federico on 08/04/2017.
 */

public class DevicesService implements IDevicesService {

    private String clientName = "pgj";
    private String locationName = "locatecdmx";
    private String deviceName = "device1";

    private IDevicesDAO dao;

    public DevicesService() {
        dao = new DevicesDAO(clientName, locationName);
    }

    @Override
    public void addListener(String deviceName, DevicesListener devicesListener) {
        dao.addListener(deviceName, devicesListener);
    }

    @Override
    public void update(Device device) {
        dao.update(device);
    }
}
