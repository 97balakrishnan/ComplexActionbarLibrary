package com.balakrishnan.complexactionbar;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ComplexActionBar extends FrameLayout {

    private ArrayList<String> arrayList=new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private boolean isOptionsOpen=false;

    public OnItemClickListener getOnMenuItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{

        public void onItemClicked(int position);

    }
    protected void itemClicked(int position){
        if(onItemClickListener!=null){
            onItemClickListener.onItemClicked(position);
        }
    }
    public ComplexActionBar(@NonNull Context context) {
        this(context,null);
    }

    public ComplexActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }
    public ComplexActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final Context cont=context;
        addView(LayoutInflater.from(context).inflate(R.layout.appbar,this,false));

        
        final ArrayAdapter adapter = new ArrayAdapter<String>(context,R.layout.list_view,arrayList);
        ListView lv = (ListView)findViewById(R.id.options_list);
        lv.setAdapter(adapter);

        ImageView img = findViewById(R.id.toolbar_logo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOptionsOpen) {
                    arrayList.add("1");
                    arrayList.add("2");
                    arrayList.add("3");
                    isOptionsOpen=true;
                    adapter.notifyDataSetChanged();
                    System.out.println("heelllo");
                }
                else
                {
                    isOptionsOpen=false;
                    arrayList.clear();
                    adapter.notifyDataSetChanged();

                }
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
                itemClicked(position);
            }
        });

    }

    public void inflateMenu(ArrayList<String> menuList)
    {

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
}
