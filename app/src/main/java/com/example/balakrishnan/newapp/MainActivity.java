package com.example.balakrishnan.newapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.balakrishnan.complexactionbar.ComplexActionBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final ComplexActionBar c = new ComplexActionBar(getApplicationContext());
        c.setActivity(this);
        c.setFragment(R.id.content_main);

        android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.my_fragment,new FirstFragment());
        ft.commit();

        String[] s={"first","second","third"};
        c.setMenuList(s);
        c.getMenu().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"Item "+position+" was clicked",Toast.LENGTH_LONG).show();
                switch(position)
                {
                    case 0:
                        android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.my_fragment,new FirstFragment());
                        ft.commit();
                        break;
                    case 1:
                        android.support.v4.app.FragmentTransaction ft1=getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.my_fragment,new SecondFragment());
                        ft1.commit();
                        break;
                    case 2:
                        android.support.v4.app.FragmentTransaction ft2=getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.my_fragment,new ThirdFragment());
                        ft2.commit();
                        break;


                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
