package jp.kassaman.checker;

import java.util.ArrayList;

import jp.kassaman.checker.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Tab1Fragment extends AbstractTabFragment {

    public static final String TAG = "Tab1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       
        return inflater.inflate(R.layout.tab1layout, container, false);
    }

    @Override
    public ArrayList<Data> getList() {

        ArrayList<Data> array = (ArrayList<Data>) FIleSave.readObjectFromFile(getActivity(), FIleSave.produce);
        if (array == null) {
            return null;

        }

        ArrayList<Data> shiwake = new ArrayList<Data>();
        
        for (Data d : array) {

            if ( d.getLimit() < 4) {
                
                shiwake.add(d);

            }
            

        }

        return shiwake;
    }
    
    

    
}
