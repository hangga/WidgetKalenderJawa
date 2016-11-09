package id.web.hangga.widgetjawa;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.web.hangga.widgetjawa.Utils.HijriCalendar;
import id.web.hangga.widgetjawa.Utils.JavaneseCalenderUtils;

public class MyService extends Service {

    Calendar calendar;
    public static String ACTION_UPDATE = "update";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(null, flags, startId);
        }

        String action = intent.getAction();
        if (action == null) {
            return super.onStartCommand(intent, flags, startId);
        }

        Log.d("JAWAW", String.format("onStartCommand: %s", action));

        if (action.equals(ACTION_UPDATE)) {
            int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            for (int widgetId : allWidgetIds) {
                update(widgetId);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void update(int widgetId){
        Log.d("JAWAW", "yak update view");
        calendar = Calendar.getInstance();
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.java_widget);
        views.setTextViewText(R.id.txt_hari, JavaneseCalenderUtils.getDina(calendar));
        views.setTextViewText(R.id.txt_pasaran, JavaneseCalenderUtils.getPasaran(calendar));
        views.setTextViewText(R.id.txt_date_hijri, HijriCalendar.getSimpleDate(calendar));
        views.setTextViewText(R.id.txt_date_georgian, new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss").format(calendar.getTime()));

        // Push update for this widget to the home screen
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(widgetId, views);
    }
}
