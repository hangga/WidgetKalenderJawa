package id.web.hangga.widgetjawa;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.web.hangga.widgetjawa.Utils.HijriCalendar;
import id.web.hangga.widgetjawa.Utils.JavaneseCalenderUtils;

public class MyService extends Service {

    Calendar calendar = Calendar.getInstance();

    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void update(){

        RemoteViews views = new RemoteViews(getPackageName(), R.layout.java_widget);
        views.setTextViewText(R.id.txt_hari, JavaneseCalenderUtils.getDina(calendar));
        views.setTextViewText(R.id.txt_pasaran, JavaneseCalenderUtils.getPasaran(calendar));
        views.setTextViewText(R.id.txt_date_hijri, HijriCalendar.getSimpleDate(calendar));
        views.setTextViewText(R.id.txt_date_georgian, new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss").format(calendar.getTime()));

    }
}
