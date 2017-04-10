package com.bintics.tracked.service;

import com.bintics.tracked.bean.Device;

import java.util.List;

/**
 * Created by federico on 08/04/2017.
 */

public interface IDevicesService {

    void addListener(String deviceName, DevicesListener devicesListener);

    void update(Device device);

    public interface DevicesListener {
        void onReceive(Device device);
    }
}
