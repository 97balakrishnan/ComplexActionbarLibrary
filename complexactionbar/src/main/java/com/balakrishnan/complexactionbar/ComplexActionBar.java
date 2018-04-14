package com.balakrishnan.complexactionbar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

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
    private void setMenu()
    {
        System.out.println("This function is called");
        adapter = new ArrayAdapter<String>(context,R.layout.list_view,menuList);
        final ListView lv = activity.findViewById(R.id.options_list);
        lv.setAdapter(adapter);

        ImageView img = activity.findViewById(R.id.toolbar_logo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOptionsOpen) {
                    isOptionsOpen=true;
                    for(int i=0;i<arrayList.size();i++)
                    {
                        System.out.println(arrayList.get(i));
                        menuList.add(arrayList.get(i));
                    }
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    isOptionsOpen=false;
                    menuList.clear();
                    adapter.notifyDataSetChanged();

                }
            }
        });


    }
    Context context;
    ArrayAdapter adapter;
    public ComplexActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        addView(LayoutInflater.from(context).inflate(R.layout.appbar,this,false));




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
}
