package jp.kassaman.checker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import jp.kassaman.checker.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<Data> {

    public DataAdapter(Context context, int resource, int textViewResourceId, List<Data> objects) {
        super(context, resource, textViewResourceId, objects);
        
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        Data data = getItem(position);

        TextView pro = (TextView) v.findViewById(R.id.productTExt);
        pro.setText(data.getProductname());

        TextView num = (TextView) v.findViewById(R.id.numText);
        num.setText(Integer.toString(data.getNumber()) + "個");

        TextView limit = (TextView) v.findViewById(R.id.limitText);

        Calendar cal = data.getDate();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        limit.setText(year + "年" + (month + 1) + "月" + day + "日");
        
        
        TextView cou = (TextView) v.findViewById(R.id.countText);
        cou.setText("あと"+data.getLimit() + "日");
        
     /*   if(data.getLimit() == 0){
            cou.setText("今日までです。");
        }
       if(data.getLimit() < 0){
            cou.setText("期限切れです。");
        } 
        */
        /*ListViewの中での話*/
        if(data.getImageDataName() != null){
            
            ImageView image = (ImageView) v.findViewById(R.id.imageView1);
            
            FileInputStream fis = null;
            try {
                fis = getContext().openFileInput(data.getImageDataName());
            } catch (FileNotFoundException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
            Bitmap bm = BitmapFactory.decodeStream(fis);
            image.setImageBitmap(bm);
        }
       
        

        return v;
    }

}
