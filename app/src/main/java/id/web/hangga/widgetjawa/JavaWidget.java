package id.web.hangga.widgetjawa;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


/**
 * Implementation of App Widget functionality.
 */
public class JavaWidget extends AppWidgetProvider {

    //private PendingIntent service = null;
    //public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName componentName = new ComponentName(context, JavaWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(componentName);

        Intent intent = new Intent(context.getApplicationContext(), MyService.class);
        intent.setAction(MyService.ACTION_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
        context.startService(intent);

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        m.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 1, pendingIntent);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, JavaWidget.class);
        onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(componentName));
    }
}

