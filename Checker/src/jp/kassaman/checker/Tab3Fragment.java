package jp.kassaman.checker;

import java.util.ArrayList;

import jp.kassaman.checker.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab3Fragment extends AbstractTabFragment {
    
    public static final String TAG = "Tab3";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3layout, container,false);
    }

    @Override
    public ArrayList<Data> getList() {
        ArrayList<Data> array = (ArrayList<Data>) FIleSave.readObjectFromFile(getActivity(), FIleSave.produce);
        if(array == null){
            return null;
        }
        ArrayList<Data> shiwake = new ArrayList<Data>();
        for(Data d:array){
            if(d.getLimit()  > 7){
                shiwake.add(d);
            }
        }
         return shiwake;
    }
}
