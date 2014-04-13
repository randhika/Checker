package jp.kassaman.checker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import jp.kassaman.checker.R;

import android.R.integer;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;

public class TimerService extends Service {

    private Timer timer;
    private TimerTask task = new TimerTask() {

        @Override
        public void run() {
            String str = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Listsbox", "7");
            int n;
            n = Integer.parseInt(str);
            Calendar cale = Calendar.getInstance();
            int hour = cale.get(Calendar.HOUR);

            if (n == hour) {

                ArrayList<Data> array = (ArrayList<Data>) FIleSave.readObjectFromFile(getApplicationContext(), FIleSave.produce);
                if (array == null) {
                    array = new ArrayList<Data>();

                }

                for (Data d : array) {
                    long limit = d.getLimit();
                    if (limit == 0L) {
                        // Notification:通知
                        NotificationManager no = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notifi = new Notification(R.drawable.ic_launcher, "����؂�̂��m�点", System.currentTimeMillis());

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        PendingIntent content = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                        notifi.setLatestEventInfo(getApplicationContext(), "Checker", "����؂�̂��m�点", content);
                        
                        no.notify(R.string.app_name, notifi);
                        
                        break;
                    }

                }

            }

        }
    };

    @Override
    public void onCreate() {
        
        super.onCreate();
        timer = new Timer();

        Calendar calen = Calendar.getInstance();
        calen.add(Calendar.HOUR, 1);
        calen.set(Calendar.MINUTE, 0);
        calen.set(Calendar.SECOND, 0);

        timer.scheduleAtFixedRate(task, calen.getTime(), 3600000L);
    }

    @Override
    public IBinder onBind(Intent intent) {
       
        return null;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
