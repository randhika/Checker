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
import android.util.Log;
import android.widget.Toast;

public class TimerService extends Service {

    private Timer timer;
    private TimerTask task = new TimerTask() {

        @Override
        public void run() {

          
            String str = PreferenceManager.getDefaultSharedPreferences(
                    getApplicationContext()).getString("Listsbox", "7");
            int n;
            n = Integer.parseInt(str);

            Calendar cale = Calendar.getInstance();
            int hour = cale.get(Calendar.HOUR_OF_DAY);

            if (n == hour) {

                ArrayList<Data> array = (ArrayList<Data>) FIleSave
                        .readObjectFromFile(getApplicationContext(),
                                FIleSave.produce);
                if (array == null) {
                    array = new ArrayList<Data>();

                }

                for (Data d : array) {
                    long limit = d.getLimit();
                    if (limit == 0L) {

                        /*--通知--*/
                        // NofificationManagerの参照を取得
                        NotificationManager no = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        //一瞬出てくる文字
                        Notification notifi = new Notification(
                                R.drawable.ic_launcher, "期限切れです。",
                                System.currentTimeMillis());

                        Intent intent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        PendingIntent content = PendingIntent.getActivity(
                                getApplicationContext(), 0, intent, 0);

                        notifi.setLatestEventInfo(getApplicationContext(),
                                "Checker",d.getProductname() + "の期限が今日までです。", content);

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
