package com.bintics.tracked;

import android.*;
import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.bintics.tracked.bean.Coord;
import com.bintics.tracked.bean.Device;
import com.bintics.tracked.location.LocationService;
import com.bintics.tracked.service.DevicesService;
import com.bintics.tracked.service.IDevicesService;

import java.util.List;

import static android.Manifest.permission.*;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingSaveSettingDevice;
    private EditText txtName;
    private EditText txtLabel;
    private Switch swStatus;
    private IDevicesService deviceService;
    private Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();

        // startService(new Intent(this, LocationService.class));

        deviceService = new DevicesService();
        deviceService.addListener("device1", new IDevicesService.DevicesListener() {
            @Override
            public void onReceive(Device device) {
                MainActivity.this.device = device;
            }
        });
        startLocation();
    }

    private void startLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String bestProvider = getBestProbider(lm);
        regiterListener(lm, bestProvider);
    }

    private String getBestProbider(LocationManager lm) {
        String str = "";
        List<String> list = lm.getAllProviders();
        for (String s : list) {
            str += s;
        }
        return str;
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionName}, permissionRequestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSON_LOCATION_STATE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Permiso concedido!", Toast.LENGTH_LONG).show();
                    startLocation();
                }
                break;
        }
    }

    private final int REQUEST_PERMISSON_LOCATION_STATE = 1;

    private void regiterListener(LocationManager lm, String bestProvider) {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, REQUEST_PERMISSON_LOCATION_STATE);
            return;
        }

        final long TIME = 1500;
        LocationListener lListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                String msg = "Latitud:{0} Longitud:{1}".replace("{0}",String.valueOf(location.getLatitude())).replace("{1}", String.valueOf(location.getLongitude()));
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                Coord cords = MainActivity.this.device.getCords();
                cords.setLat(location.getLatitude());
                cords.setLng(location.getLongitude());
                deviceService.update(MainActivity.this.device);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME, 0, lListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, TIME, 0, lListener);
    }

    private void initializeComponents() {
        txtLabel = (EditText) findViewById(R.id.txtLabelDevice);
        txtName = (EditText) findViewById(R.id.txtNameDevice);
        swStatus = (Switch) findViewById(R.id.status_device);
    }
}
