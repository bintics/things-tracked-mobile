package com.bintics.tracked.data;

import com.bintics.tracked.bean.Device;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by andru-fb on 20/03/17.
 */

public class FirebaseDAO implements IFirebaseDAO {

    final String URL_DATA_ROOT = "clients/pgj/locates/locatecdmx/devices";
    final String STORES_ = "clients/locates/locatecdmx/devices";
    private FirebaseDatabase database;

    public FirebaseDAO() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void pushDevice(Device device) {
        database.getReference(URL_DATA_ROOT).child(device.getUid()).setValue(device);

    }

    @Override
    public void updateLocationDevice(final String uid, final double latitude, final double longitude) {
        database.getReference(URL_DATA_ROOT).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Device device = dataSnapshot.getValue(Device.class);
                if (device != null){
                    device.setLatitude(latitude);
                    device.setLongitude(longitude);

                    database.getReference(URL_DATA_ROOT).child(uid).setValue(device);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
