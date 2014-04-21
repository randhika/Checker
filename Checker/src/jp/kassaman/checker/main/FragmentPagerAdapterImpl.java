package jp.kassaman.checker.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import jp.kassaman.checker.Tab1Fragment;
import jp.kassaman.checker.Tab2Fragment;
import jp.kassaman.checker.Tab3Fragment;

public class FragmentPagerAdapterImpl extends FragmentPagerAdapter {

    public FragmentPagerAdapterImpl(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Tab1Fragment();
        } else if (position == 1) {
            return new Tab2Fragment();
        } else {
            return new Tab3Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
