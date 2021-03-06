package ruby.accelerometer2;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;


/**
 * Created by ruby__000 on 14/11/2016.
 */

public class Receiver extends WearableListenerService {
    private static final String TAG = "Receiver";
    private DataLayer dataLayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Wear Receiver created");
        dataLayer = dataLayer.getInstance(this);
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);

        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                DataItem item = dataEvent.getDataItem();
                Log.d(TAG, "DataItem changed: " + item.getUri());
            }
        }
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "Received message: " + messageEvent.getPath());
        if (messageEvent.getPath().equals("/start")) {
            startService(new Intent(this, startSenseService.class));
        }
        if (messageEvent.getPath().equals("/stop")) {
            stopService(new Intent(this, startSenseService.class));
        }
    }
}
