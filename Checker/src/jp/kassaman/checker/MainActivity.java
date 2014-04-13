package jp.kassaman.checker;

import jp.kassaman.checker.R;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TabHost;

public class MainActivity extends Activity {

    private TabListener tabListener1 = new TabListener() {

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {

            Fragment f = getFragmentManager().findFragmentByTag(Tab1Fragment.TAG);
            ft.remove(f);

        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {

            ft.add(R.id.fragment_container, new Tab1Fragment(), Tab1Fragment.TAG);
        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // TODO �����������ꂽ���\�b�h�E�X�^�u

        }
    };

    private TabListener tabListener2 = new TabListener() {

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            Fragment f = getFragmentManager().findFragmentByTag(Tab2Fragment.TAG);
            ft.remove(f);

        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {

            ft.add(R.id.fragment_container, new Tab2Fragment(), Tab2Fragment.TAG);

        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // TODO �����������ꂽ���\�b�h�E�X�^�u

        }
    };

    private TabListener tabListener3 = new TabListener() {

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            Fragment f = getFragmentManager().findFragmentByTag(Tab3Fragment.TAG);
            ft.remove(f);
        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            ft.add(R.id.fragment_container, new Tab3Fragment(), Tab3Fragment.TAG);

        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // TODO �����������ꂽ���\�b�h�E�X�^�u

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionbar.addTab(actionbar.newTab().setText("3日以内").setTabListener(tabListener1));
        actionbar.addTab(actionbar.newTab().setText("7日以内").setTabListener(tabListener2));
        actionbar.addTab(actionbar.newTab().setText("日持ちのするもの").setTabListener(tabListener3));

        boolean on = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("checkbox", true);
        if (on) {
            startService(new Intent(this, TimerService.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        
        if(requestCode == 200){
            boolean on = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("checkbox", true);
            if (on) {
                startService(new Intent(this, TimerService.class));
            }else {
                stopService(new Intent(this, TimerService.class));
            }
            
        }
        
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivityForResult(intent, 100);
        } else if (item.getItemId() == R.id.setteing_timer) {
            Intent time = new Intent(this, PreferenceActivitySample.class);
            startActivityForResult(time, 200);
        }

        return super.onOptionsItemSelected(item);
    }

}
