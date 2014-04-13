package jp.kassaman.checker;

import java.util.ArrayList;

import jp.kassaman.checker.R;
import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.fortysevendeg.swipelistview.SwipeListViewListener;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public abstract class AbstractTabFragment extends Fragment {

    ArrayList<Data> array;

    private BaseSwipeListViewListener base = new BaseSwipeListViewListener() {

        public void onOpened(int position, boolean toRight) {

            // �t�@�C���ォ�����
            Data delete = array.get(position);
            ArrayList<Data> del = (ArrayList<Data>) FIleSave.readObjectFromFile(getActivity(), FIleSave.produce);
            del.remove(delete);

            FIleSave.writeObjectToFile(getActivity(), del, FIleSave.produce);

            // ��ʏォ�����
            setListView();

        };
    };
    
    public void onStart() {
        super.onStart();
        setListView();
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListView();
    }

    // ListView�̒��g���X�V����
    protected void setListView() {
        array = getList();

        if (array == null) {
            array = new ArrayList<Data>();
        }
        SwipeListView list = (SwipeListView) getView().findViewById(R.id.listView1);
        list.setAdapter(new DataAdapter(getActivity(), R.layout.listitem, R.id.productTExt, array));
        list.setSwipeListViewListener(base);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            setListView();
        }
    }

    abstract public ArrayList<Data> getList();
    
    

}
