package jp.kassaman.checker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.widget.ListView;


public class DataComparator implements Comparator<Data>{

    List<Data> list = new ArrayList<Data>();
    @Override
    public int compare(Data lhs, Data rhs) {
        
        Calendar no1 = lhs.getDate();
        Calendar no2 = rhs.getDate();
        
        if(no1.getTimeInMillis() > no2.getTimeInMillis()){
            
            return 1;
            
        }else if(no1.getTimeInMillis() < no2.getTimeInMillis()){
            return -1;
        }
        return 0;
        
        
        
        
       
     
    }
    
  
}
