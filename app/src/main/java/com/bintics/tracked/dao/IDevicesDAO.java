package com.bintics.tracked.dao;

import com.bintics.tracked.bean.Coord;
import com.bintics.tracked.bean.Device;
import com.bintics.tracked.service.IDevicesService;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by andru-fb on 20/03/17.
 */

public interface IDevicesDAO {

    void saveDevice(Device device);

    void updateLocation(Coord coord);

    List<Device> getAllDevices();

    String getRootStorage();

    DatabaseReference getReference();

    void addListener(String deviceName, IDevicesService.DevicesListener devicesListener);

    void update(Device device);
}
