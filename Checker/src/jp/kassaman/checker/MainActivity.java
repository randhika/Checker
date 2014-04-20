package jp.kassaman.checker;

import jp.kassaman.checker.R;
import jp.kassaman.checker.main.FragmentPagerAdapterImpl;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
    
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.fragment_container);
        mViewPager.setAdapter(new FragmentPagerAdapterImpl(getFragmentManager()));
        mViewPager.setOnPageChangeListener(new OnPageChangeListenerImpl());

        final ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionbar.addTab(actionbar.newTab().setText("3日以内").setTabListener(new TabListenerImpl()));
        actionbar.addTab(actionbar.newTab().setText("7日以内").setTabListener(new TabListenerImpl()));
        actionbar.addTab(actionbar.newTab().setText("日持ちのするもの").setTabListener(new TabListenerImpl()));

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

    /*
     * ViewPager（ページ）になにか起こった時に制御を書くクラス
     */
    private class OnPageChangeListenerImpl implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {}

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}

        @Override
        public void onPageSelected(int arg0) {
            // ページが切り替えられたとき、タブの表示を変える
            getActionBar().setSelectedNavigationItem(arg0);
        }
        
    }

    /*
     * タブに何か起こった時の制御を書くクラス
     */
    private class TabListenerImpl implements TabListener {

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {}

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // タブが選択された時、ページを切り替えする
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
        
    }
}
