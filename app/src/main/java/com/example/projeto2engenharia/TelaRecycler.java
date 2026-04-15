package com.example.projeto2engenharia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TelaRecycler extends AppCompatActivity {
private RecyclerView recycler;
private BD bd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_recycler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recycler = findViewById(R.id.recycler);
        bd = new BD(this, "banco", null, 1);

        Adaptador adaptador = new Adaptador(bd.getLista(), new Adaptador.OnItemClick() {
            @Override
            public void onClick(Pessoa p) {
                startActivity(new Intent(TelaRecycler.this, MainActivity.class));
            }
        });


        RecyclerView.LayoutManager manager = new GridLayoutManager(this,2);
        recycler.setLayoutManager(manager);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adaptador);
    }
}