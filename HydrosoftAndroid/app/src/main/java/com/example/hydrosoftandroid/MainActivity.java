package com.example.hydrosoftandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnContinua = (Button) findViewById(R.id.btnContinua);
        btnContinua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Benvenuto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ActivityVisDati.class);
                startActivity(intent);
            }
        });
    }
}