package com.example.indevapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.indevapp.navigation.OtherFragment;
import com.example.indevapp.navigation.TogetherFrament;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView my_navigation;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_navigation = findViewById(R.id.my_navigation);

        my_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.homeItem || id == R.id.searchItem || id == R.id.photoItem ||
                id == R.id.favoriteItem){
                    fragment = new OtherFragment();
                } else {
                    fragment = new TogetherFrament();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
                return true;
            }
        });
    }

}