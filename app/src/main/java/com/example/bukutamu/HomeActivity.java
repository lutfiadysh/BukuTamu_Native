package com.example.bukutamu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void InputData(View v){
        Intent input = new Intent(getBaseContext(),InputActivity.class);
        startActivity(input);
    }

    public void LihatData(View view){
        Intent lihat = new Intent(getBaseContext(),LihatActivity.class);
        startActivity(lihat);
    }
}
