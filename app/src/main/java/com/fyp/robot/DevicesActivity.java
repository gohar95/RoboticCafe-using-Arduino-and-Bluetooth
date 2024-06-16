package com.fyp.robot;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fyp.robot.CustomerActivity;
import com.fyp.robot.FaceActivity;
import com.fyp.robot.R;
import com.fyp.robot.robot.BluetoothUtil;

import java.util.ArrayList;
import java.util.Collections;

public class DevicesActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private final ArrayList<BluetoothDevice> listItems = new ArrayList<>();
    private ArrayAdapter<BluetoothDevice> listAdapter;
    private boolean permissionMissing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH))
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listAdapter = new ArrayAdapter<BluetoothDevice>(this, 0, listItems) {
            @NonNull
            @Override
            public View getView(int position, View view, @NonNull ViewGroup parent) {
                BluetoothDevice device = listItems.get(position);
                if (view == null)
                    view = getLayoutInflater().inflate(R.layout.device_list_item, parent, false);
                TextView text1 = view.findViewById(R.id.text1);
                TextView text2 = view.findViewById(R.id.text2);
                @SuppressLint("MissingPermission") String deviceName = device.getName();
                text1.setText(deviceName);
                text2.setText(device.getAddress());
                return view;
            }
        };
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_devices, menu);
        if (permissionMissing)
            menu.findItem(R.id.bt_refresh).setVisible(true);
        if (bluetoothAdapter == null)
            menu.findItem(R.id.bt_settings).setEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.bt_settings) {
            Intent intent = new Intent();
            intent.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(intent);
            return true;
        } else if (id == R.id.bt_refresh) {
            refresh();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("MissingPermission")
    void refresh() {
        listItems.clear();
        if (bluetoothAdapter != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                permissionMissing = checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED;
            }
            if (!permissionMissing) {
                for (BluetoothDevice device : bluetoothAdapter.getBondedDevices())
                    if (device.getType() != BluetoothDevice.DEVICE_TYPE_LE)
                        listItems.add(device);
                Collections.sort(listItems, BluetoothUtil::compareTo);
            }
        }
        if (bluetoothAdapter == null)
            setEmptyText("<bluetooth not supported>");
        else if (!bluetoothAdapter.isEnabled())
            setEmptyText("<bluetooth is disabled>");
        else if (permissionMissing)
            setEmptyText("<permission missing, use REFRESH>");
        else
            setEmptyText("<no bluetooth devices found>");
        listAdapter.notifyDataSetChanged();
    }

    void setEmptyText(String text) {
        TextView emptyText = findViewById(R.id.empty);
        emptyText.setText(text);
    }

    public void onListItemClick(View v) {
        int position = (int) v.getTag();
        BluetoothDevice device = listItems.get(position);
        Bundle args = new Bundle();
        args.putString("device", device.getAddress());

        Intent intent = new Intent(this, CustomerActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }
}
