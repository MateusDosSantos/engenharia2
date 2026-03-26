package com.example.projeto2engenharia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  private ImageButton imageButton, imageButton2;
  private Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);

        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this);

        btn1 = findViewById(R.id.button3);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.button4);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.button5);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == imageButton){
            startActivity(new Intent(this, TelaVideo.class));
        }
        if(view == imageButton2){
            startActivity(new Intent(this, TelaSom.class));
        }
        if(view == btn1){
            startActivity(new Intent(this, TerlaWebView1.class));
        }
        if(view == btn2){
            startActivity(new Intent(this, TelaWebView2.class));

        }
        if(view == btn3){
            startActivity(new Intent(this, TelaSemXML.class));

        }

    }
}