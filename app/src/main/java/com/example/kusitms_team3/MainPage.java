package com.example.kusitms_team3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kusitms_team3.MainPagePackage.MainPage_Fragment1;
import com.example.kusitms_team3.MainPagePackage.MainPage_Fragment2;
import com.example.kusitms_team3.MainPagePackage.MainPage_Fragment3;
import com.example.kusitms_team3.MainPagePackage.MainPage_Fragment4;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainPage extends AppCompatActivity {
    private MainPage_Fragment1 mainPage_fragment1 = new MainPage_Fragment1();
    private MainPage_Fragment2 mainPage_fragment2 = new MainPage_Fragment2();
    private MainPage_Fragment3 mainPage_fragment3 = new MainPage_Fragment3();
    private MainPage_Fragment4 mainPage_fragment4 = new MainPage_Fragment4();


    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bottomNavigationView = findViewById(R.id.main_bottomNav);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrame, mainPage_fragment1).commitAllowingStateLoss();

        // bottom navigation click action
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.fragment1: {
                        transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                                .replace(R.id.mainFrame, mainPage_fragment1).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.fragment2: {
                        transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                                .replace(R.id.mainFrame, mainPage_fragment2).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.fragment3: {
                        transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                                .replace(R.id.mainFrame, mainPage_fragment3).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.fragment4: {
                        transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                                .replace(R.id.mainFrame, mainPage_fragment4).commitAllowingStateLoss();
                        return true;
                    }
                }
                return false;
            }
        });
    }
}