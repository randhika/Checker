package jp.kassaman.checker;

import jp.kassaman.checker.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceActivitySample extends PreferenceActivity{
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        super.onCreate(savedInstanceState);
   addPreferencesFromResource(R.xml.pref);
    }

}
