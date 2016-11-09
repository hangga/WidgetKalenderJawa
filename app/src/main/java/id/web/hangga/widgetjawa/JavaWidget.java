package id.web.hangga.widgetjawa;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;


/**
 * Implementation of App Widget functionality.
 */
public class JavaWidget extends AppWidgetProvider {

    private PendingIntent service = null;
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";

    /*static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Calendar calendar = Calendar.getInstance();

        CharSequence widgetText = context.getString(R.string.app_name);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.java_widget);
        views.setTextViewText(R.id.txt_hari, JavaneseCalenderUtils.getDina(calendar));
        views.setTextViewText(R.id.txt_pasaran, JavaneseCalenderUtils.getPasaran(calendar));
        views.setTextViewText(R.id.txt_date_hijri, HijriCalendar.getSimpleDate(calendar));
        views.setTextViewText(R.id.txt_date_georgian, new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss").format(calendar.getTime()));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }*/

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        /*for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
        Calendar TIME = Calendar.getInstance();
        AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, MyService.class);
        if (service == null){
            service = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        }
        m.setRepeating(AlarmManager.RTC, TIME.getTime().getTime(), 1000 * 1/*lima detik*/, service);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

