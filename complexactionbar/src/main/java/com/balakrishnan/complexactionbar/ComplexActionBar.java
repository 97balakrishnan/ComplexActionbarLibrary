package com.balakrishnan.complexactionbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.gesture.GestureOverlayView;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ComplexActionBar extends FrameLayout {

    private List<String> arrayList=new ArrayList<>();
    private List<String> menuList= new ArrayList<>();
    private boolean isOptionsOpen=false;



    Activity activity;
    public void setActivity(Activity activity)
    {
        this.activity=activity;
        Toolbar t = activity.findViewById(R.id.toolbar);
        t.setContentInsetsAbsolute(0,0);
    }
    public void setActionbarColor(Color color)
    {
        LinearLayout ll =activity.findViewById(R.id.ll_container);
        ll.setBackgroundColor(color.hashCode());
    }

    public void setActionbarColor(int color)
    {
        LinearLayout ll =activity.findViewById(R.id.ll_container);
        ll.setBackgroundColor(color);
    }
    public void setMenuColor(int color)
    {
        ListView ll =activity.findViewById(R.id.options_list);
        ll.setBackgroundColor(color);
    }
    public void setMenuColor(Color color)
    {
        ListView ll =activity.findViewById(R.id.options_list);
        ll.setBackgroundColor(color.hashCode());

    }
    public void setTitleColor(Color color)
    {
        TextView t= activity.findViewById(R.id.title);
        t.setTextColor(color.hashCode());
    }
    public void setTitleColor(int color)
    {
        TextView t= activity.findViewById(R.id.title);
        t.setTextColor(color);
    }
    public void setTitle(String title)
    {
        TextView t= activity.findViewById(R.id.title);
        t.setText(title);
    }
    public void setMenuTextColor(Color color)
    {
        ListView ll =activity.findViewById(R.id.options_list);
        Adapter a =ll.getAdapter();
        //System.out.println(a.getCount()+" "+ll.getChildCount());
        /*
        for(int i=0;i<a.getCount();i++)
        {
            TextView t =(TextView)ll.getChildAt(i);
            //TextView t = activity.findViewById(R.id.list_text);
            t.setTextColor(color.hashCode());
        }*/
    }
    public void setMenuTextColor(int color)
    {

        ListView ll =activity.findViewById(R.id.options_list);
        Adapter a =ll.getAdapter();
        System.out.println(a.getCount()+" "+ll.getChildCount());

        for(int i=0;i<a.getCount();i++)
        {
            TextView t =(TextView)ll.getAdapter().getView(i,null,ll);
            //TextView t = activity.findViewById(R.id.list_text);
            t.setTextColor(color);
        }
    }
    public ComplexActionBar(@NonNull Context context) {
        this(context,null);
    }

    public ComplexActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }
    public void setMenuList(ArrayList<String> arrayList)
    {
        this.arrayList=arrayList;
        setMenu();

    }
    public void setMenuList(String[] strings)
    {
        for(int i=0;i<strings.length;i++)
            arrayList.add(strings[i]);

        System.out.println("array list is set size:"+arrayList.size());
        setMenu();
        //menuList=arrayList;
        //System.out.println("MenuList oda size:"+menuList.size());
        //adapter.notifyDataSetChanged();
    }

    private int openHeight;
    private ImageView img;
    private View f;
    @SuppressLint("ClickableViewAccessibility")
    private void setMenu()
    {
        f =activity.findViewById(fragment);
        System.out.println("This function is called");
        adapter = new ArrayAdapter<String>(context,R.layout.list_view,R.id.list_text,menuList);
        final ListView lv = activity.findViewById(R.id.options_list);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                if(v.getHeight()!=0)
                {
                    if(openHeight==0)
                    {
                        lv.setVisibility(GONE);
                    }
                    openHeight=v.getHeight();
                }

            }

        });
        for(int i=0;i<arrayList.size();i++)
        {
            menuList.add(arrayList.get(i));
        }


        img = activity.findViewById(R.id.toolbar_logo);

        activity.findViewById(R.id.ll_container).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("action:"+event.getY());
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                    downy=event.getY();
                else if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getY()-downy<=1)
                        clicker();
                    else if(event.getY()-downy>10 && !isOptionsOpen)
                        clicker();
                    else if(downy-event.getY()>10 && isOptionsOpen)
                        clicker();
                }
                return true;
            }

        });
        activity.findViewById(R.id.options_list).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("action:"+event.getY());
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    downy = event.getY();
                    return false;
                }
                else if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(downy-event.getY()>20 && isOptionsOpen)
                        clicker();
                }
                return true;
            }
        });/*
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
*/


    }
    private void clicker()
    {
        final ListView lv = activity.findViewById(R.id.options_list);
        if(!isOptionsOpen) {
            isOptionsOpen=true;
            menuList=arrayList;
            adapter.notifyDataSetChanged();
            f.animate().translationY(openHeight).start();
            lv.setVisibility(VISIBLE);
        }
        else
        {
            isOptionsOpen=false;
            lv.setVisibility(GONE);
            lv.postOnAnimation(new Runnable() {
                @Override
                public void run() {
                    f.animate().translationY(0).start();

                }
            });
            //lv.animate().translationYBy(-lv.getHeight()).setDuration(200).start();


        }


    }
    Context context;
    ArrayAdapter adapter;
    private float downx,downy;
    @SuppressLint("ClickableViewAccessibility")
    public ComplexActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        addView(LayoutInflater.from(context).inflate(R.layout.appbar,this,false));

        openHeight=0;

    }

    @Override
    public void setForegroundGravity(int foregroundGravity) {
        super.setForegroundGravity(foregroundGravity);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void setMeasureAllChildren(boolean measureAll) {
        super.setMeasureAllChildren(measureAll);
    }

    @Override
    public boolean getConsiderGoneChildrenWhenMeasuring() {
        return super.getConsiderGoneChildrenWhenMeasuring();
    }

    @Override
    public boolean getMeasureAllChildren() {
        return super.getMeasureAllChildren();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return super.shouldDelayChildPressedState();
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return super.generateLayoutParams(lp);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }
    public ListView getMenu()
    {
        ListView l =activity.findViewById(R.id.options_list);
        return l;
    }
    private int fragment;
    public void setFragment(int fragment)
    {
        this.fragment=fragment;
        System.out.println("fragment int "+fragment);
    }
}
