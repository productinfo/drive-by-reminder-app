package at.fhj.itm10.mobcomp.drivebyreminder.receiver;

import roboguice.receiver.RoboBroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import at.fhj.itm10.mobcomp.drivebyreminder.services.NotificationService;

/**
 * Broadcast receiver to start service at boot.
 * 
 * @author Wolfgang Gaar
 */
public class ServiceBootLoader extends RoboBroadcastReceiver {

	@Override
	protected void handleReceive(Context context, Intent intent) {
		SharedPreferences preferences = PreferenceManager
        		.getDefaultSharedPreferences(context);

		if (preferences.getBoolean("appEnabled", true)) {
			Log.v(getClass().getSimpleName(),
					"preference set to true, starting service on boot...");

			Intent startServiceIntent = new Intent(context,
					NotificationService.class);
	        context.startService(startServiceIntent);
		}

		super.handleReceive(context, intent);
    }

}
