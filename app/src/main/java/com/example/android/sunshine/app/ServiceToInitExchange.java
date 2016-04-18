package com.example.android.sunshine.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;

public class ServiceToInitExchange extends WearableListenerService {

    private static final String TAG = ServiceToInitExchange.class.getSimpleName();
    private static final String KEY_PATH_TO_WEAR_DATA = "/keyforAskdatarequest";

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);
        //SunshineSyncAdapter.syncImmediately(this);
        Log.d(TAG, "Message onDataChanged !!!");


        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() != DataEvent.TYPE_CHANGED) {
                continue;
            }

            DataItem dataItem = dataEvent.getDataItem();
            if (!dataItem.getUri().getPath().equals(KEY_PATH_TO_WEAR_DATA)) {
                continue;
            }

            SunshineSyncAdapter.syncImmediately(this);

            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "Config DataItem updated:");
            }
        }
    }
}
