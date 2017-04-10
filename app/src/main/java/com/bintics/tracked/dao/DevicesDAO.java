package com.bintics.tracked.dao;

import com.bintics.tracked.bean.Coord;
import com.bintics.tracked.bean.Device;
import com.bintics.tracked.service.IDevicesService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by federico on 08/04/2017.
 */

public class DevicesDAO extends DevicesDAOAbstract {

    private DatabaseReference deviceReference;

    public DevicesDAO(String clientName, String locationName) {
        super(clientName, locationName);
    }

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
    public void addListener(String deviceName, IDevicesService.DevicesListener devicesListener) {
        //getReference().child(deviceName).addValueEventListener(new DeviceValueEvent(devicesListener));
        deviceReference = getReference().child(deviceName);
        deviceReference.addValueEventListener(new DeviceValueEvent(devicesListener));
    }

    @Override
    public void update(Device device) {
        HashMap<String, Object> h = new HashMap<>();
        h.put("/name/", device.getName());
        h.put("/connected/", device.getConnected());
        h.put("/cords/label/", device.getCords().getLabel());
        h.put("/cords/lat/", device.getCords().getLat());
        h.put("/cords/lng/", device.getCords().getLng());
        deviceReference.updateChildren(h);
    }
}

class DeviceValueEvent implements ValueEventListener {

    private final IDevicesService.DevicesListener devicesListener;

    public DeviceValueEvent(IDevicesService.DevicesListener devicesListener) {
        this.devicesListener = devicesListener;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        long count = dataSnapshot.getChildrenCount();
        Device data = dataSnapshot.getValue(Device.class);
        Coord coord = dataSnapshot.child("cords").getValue(Coord.class);
        data.setCords(coord);
        devicesListener.onReceive(data);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
