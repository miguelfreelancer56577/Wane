package app.wane.com.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import app.wane.com.soport.Tracking;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TrackingService extends IntentService {
    //actions
    public static final String ACTION_RUN_ISERVICE = "run";


    public TrackingService() {
        super("TrackingService");
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_RUN_ISERVICE.equals(action)) {
                Tracking tracking = new Tracking(TrackingService.this, 15000, 0);
                tracking.runLocationTracking();
            }
        }
    }
}
