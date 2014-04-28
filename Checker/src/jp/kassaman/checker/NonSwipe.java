package jp.kassaman.checker;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class NonSwipe extends ViewPager{

    public NonSwipe(Context context) {
        super(context);
        // TODO 自動生成されたコンストラクター・スタブ
    }
    
    public NonSwipe(Context context, AttributeSet atr){
        super(context,atr);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }
}
