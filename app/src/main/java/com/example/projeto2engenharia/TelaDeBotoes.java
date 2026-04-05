package com.example.projeto2engenharia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TelaDeBotoes extends AppCompatActivity {
    private ChipGroup grupoChip;
    private RadioGroup grupoRadio;
    private Switch aSwitch;
    private CheckBox check1, check2;
    private ToggleButton toggle;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout layout;
    private ProgressBar barra;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_de_botoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        grupoChip = findViewById(R.id.id1);
        grupoChip.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup chipGroup, @NonNull List<Integer> list) {
                if(chipGroup == grupoChip){
                    String selecionado = " ";
                    for(int i=0; i<list.size();i++){
                       int id = list.get(i);
                       Chip chip = findViewById(id);
                       selecionado += chip.getText()+ " ";
                   }
                   Toast.makeText(TelaDeBotoes.this, selecionado, Toast.LENGTH_SHORT).show();
                }
            }
        });
        grupoRadio = findViewById(R.id.id2);
        grupoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {
                if(radioGroup == grupoRadio){
                    String radio = " ";
                    if(i == R.id.radioButton){
                        radio = "Com Sal";
                    }
                    if(i == R.id.radioButton2){
                        radio = "Sem Sal";
                    }
                    Toast.makeText(TelaDeBotoes.this, radio, Toast.LENGTH_SHORT).show();

                }

            }
        });
        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                if(compoundButton == aSwitch){
                    Toast.makeText(TelaDeBotoes.this, Boolean.toString(b), Toast.LENGTH_SHORT).show();

                }
            }
        });
        check1 = findViewById(R.id.checkBox);
        check2 = findViewById(R.id.checkBox2);

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                if(compoundButton == check1){
                    Toast.makeText(TelaDeBotoes.this, Boolean.toString(b), Toast.LENGTH_SHORT).show();
                }
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                if(compoundButton == check2){
                    Toast.makeText(TelaDeBotoes.this, Boolean.toString(b), Toast.LENGTH_SHORT).show();
                }
            }
        });
        toggle = findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                if(compoundButton == toggle){
                    Toast.makeText(TelaDeBotoes.this, "Toggle está "+Boolean.toString(b), Toast.LENGTH_SHORT).show();
                }
            }
        });
        barra = findViewById(R.id.progressBar);
        layout = findViewById(R.id.layout);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(layout, "Clicou no FloatingActionButton", Snackbar.LENGTH_SHORT).setAction("Alterar", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int progresso = barra.getProgress();
                        progresso+=10;
                        barra.setProgress(progresso);
                    }
                }).show();
            }
        });

    }
}