package com.example.user.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendcontact(View view) {
        Intent intent = new Intent(this, AActivity.class);
        startActivity(intent);
    }
    public void sendgallery(View view) {
        Intent intent = new Intent(this, BActivity.class);
        startActivity(intent);
    }
    public void memory_game(View view) {
        Intent intent = new Intent(this, CActivity.class);
        startActivity(intent);
    }
}
