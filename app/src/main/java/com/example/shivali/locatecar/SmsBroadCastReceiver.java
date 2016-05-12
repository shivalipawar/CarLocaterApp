package com.example.shivali.locatecar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
public class SmsBroadCastReceiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE = "pdus";
    double latitude;
    double longitude;
    String carOwner;

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                String smsBody = smsMessage.getMessageBody().toString();
                //String address = smsMessage.getOriginatingAddress();

                ////if (address.contains("9175304021")) {
                    //smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr = smsBody ;
               // }
            }

                MapsActivity mapsActivity = MapsActivity.getInstance();
                extractData(smsMessageStr);
                mapsActivity.updateVehicleLocation(latitude,longitude,carOwner);
        }
    }

    private void extractData(String smsMessageStr) {
    String data[] = smsMessageStr.split("\n");
    carOwner=data[0];
    latitude = Double.parseDouble(data[1].split(":")[1]);
    longitude = Double.parseDouble(data[2].split(":")[1]);
    }

}