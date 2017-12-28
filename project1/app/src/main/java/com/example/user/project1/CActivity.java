package com.example.user.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
    }

    public void send1(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void send2(View view) {
        Intent intent = new Intent(this, level1Activity.class);
        startActivity(intent);
    }
    public void send3(View view) {
        Intent intent = new Intent(this, level2Activity.class);
        startActivity(intent);
    }
    public void send4(View view) {
        Intent intent = new Intent(this, level3Activity.class);
        startActivity(intent);
    }
}

