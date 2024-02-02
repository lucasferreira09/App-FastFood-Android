package com.example.burger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnPedir;
    ImageView fome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fome = findViewById(R.id.fome);

        fome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ac = new Intent(MainActivity.this, ActivityHost.class);
                startActivity(ac);
            }
        });
        /*
        btnPedir = findViewById(R.id.btnPedir);

        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityHost.class));
            }
        });

         */

    }
}