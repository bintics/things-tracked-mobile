package com.bintics.tracked;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.bintics.tracked.bean.Device;
import com.bintics.tracked.data.FirebaseDAO;
import com.bintics.tracked.location.LocationService;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingSaveSettingDevice;
    private EditText txtName;
    private EditText txtLabel;
    private Switch swStatus;
    private FirebaseDAO firebaseDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLabel = (EditText) findViewById(R.id.txtLabelDevice);
        txtName = (EditText) findViewById(R.id.txtNameDevice);
        swStatus = (Switch) findViewById(R.id.status_device);

        startService(new Intent(this, LocationService.class));

        floatingSaveSettingDevice = (FloatingActionButton) findViewById(R.id.ftngSaveSettingsDevice);

        floatingSaveSettingDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDAO = new FirebaseDAO();

                Device device = new Device();
                device.setUid("device" + ((int)(Math.random() * 30)));
                device.setName(txtName.getText().toString());
                device.setLabel(txtLabel.getText().toString());
                device.setConected(swStatus.isChecked());

                firebaseDAO.pushDevice(device);

                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.settings_file_app), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString(String.valueOf(R.string.uid_device), device.getUid());
                editor.putString(String.valueOf(R.string.name_device), device.getName());

                editor.commit();

                txtLabel.setText("");
                txtName.setText("");
            }
        });

        //firebaseDAO.pushDevice(device);
    }
}
