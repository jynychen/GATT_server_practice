/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidthings.gattserver;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

import java.util.Calendar;
import java.util.UUID;

/**
 * Implementation of the Bluetooth GATT Time Profile.
 * https://www.bluetooth.com/specifications/adopted-specifications
 */
public class ServiceProfile {
    private static final String TAG = ServiceProfile.class.getSimpleName();

    /* Service UUID */
    public static UUID mSERVICE = UUID.fromString("00000000-0001-1000-8000-00805f9b34fb");
    /* read & notify Characteristic */
    public static UUID read_Charact_ID = UUID.fromString("00000000-0002-1000-8000-00805f9b34fb");
    /* write Characteristic */
    public static UUID write_Charact_ID = UUID.fromString("00000000-0003-1000-8000-00805f9b34fb");
    /* read Descriptor */
    public static UUID notify_Descrip_ID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    /**
     * Return a configured {@link BluetoothGattService} instance for the
     * Current Time Service.
     */
    public static BluetoothGattService mGattService() {
        BluetoothGattService service = new BluetoothGattService(mSERVICE,
                BluetoothGattService.SERVICE_TYPE_PRIMARY);

        //Read-only characteristic, supports notifications
        BluetoothGattCharacteristic read_Charact = new BluetoothGattCharacteristic(read_Charact_ID,
                BluetoothGattCharacteristic.PROPERTY_READ | BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                BluetoothGattCharacteristic.PERMISSION_READ);
        BluetoothGattDescriptor notifyDescriptor = new BluetoothGattDescriptor(notify_Descrip_ID,
                BluetoothGattDescriptor.PERMISSION_READ | BluetoothGattDescriptor.PERMISSION_WRITE);
        read_Charact.addDescriptor(notifyDescriptor);

        /* write Characteristic */
        BluetoothGattCharacteristic write_Charact = new BluetoothGattCharacteristic(write_Charact_ID,
                BluetoothGattCharacteristic.PROPERTY_WRITE,
                BluetoothGattCharacteristic.PERMISSION_WRITE);

        service.addCharacteristic(read_Charact);
        service.addCharacteristic(write_Charact);

        return service;
    }
}
