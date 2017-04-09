package com.bintics.tracked.data;

import com.bintics.tracked.bean.Coord;
import com.bintics.tracked.bean.Device;

import java.util.List;

/**
 * Created by andru-fb on 20/03/17.
 */

public interface IDevicesDAO {

    void saveDevice(Device device);

    void updateLocation(Coord coord);

    List<Device> getAllDevices();

    Device getDevice(String deviceName);

}
