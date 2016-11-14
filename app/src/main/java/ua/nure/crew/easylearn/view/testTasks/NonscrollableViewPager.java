package ua.nure.crew.easylearn.view.testTasks;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NonscrollableViewPager extends ViewPager {

    private boolean isScrollable;

    public NonscrollableViewPager(Context context) {
        super(context);
    }

    public NonscrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isScrollable && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isScrollable && super.onInterceptTouchEvent(event);
    }

    public void setScrollable(boolean b) {
        isScrollable = b;
    }
}
