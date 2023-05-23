package com.example.indevapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.indevapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreatePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpost);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        initControl();
    }

    private void initControl() {
        Button btn_createInBodyTogether = (Button) findViewById(R.id.button_create);
        btn_createInBodyTogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CreatePostActivity.this, "게시글을 추가하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
