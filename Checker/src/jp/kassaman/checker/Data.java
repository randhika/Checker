package jp.kassaman.checker;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Data implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 2254134141837831449L;
    private long id = System.currentTimeMillis();
    private String productname ;
    private int number;
    private Calendar calDate;    
    
    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Calendar getDate() {
        return calDate;
    }
    public void setDate(Calendar date) {
        this.calDate = date;
    }
    

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        Data another = (Data)o;
        return id == another.getId();
    }
    
    public long getLimit(){
        
        //現在の時間を取得
        Calendar now = Calendar.getInstance();


        Calendar future = calDate;

        long differenceMilliSeconds = future.getTimeInMillis()
                - now.getTimeInMillis();

        long differenceDays = differenceMilliSeconds
                / (24 * 60 * 60 * 1000);

        
        
        return differenceDays;
        
        
    }
}
