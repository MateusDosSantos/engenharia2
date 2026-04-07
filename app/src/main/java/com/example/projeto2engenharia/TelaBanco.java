package com.example.projeto2engenharia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class TelaBanco extends AppCompatActivity implements View.OnClickListener{
    private TextInputEditText nome, cpf, telefone, cidade, idade, profissao;
    private Button btnSalvar, btnListar;
    private BD bd;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_banco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSalvar = findViewById(R.id.salvar);
        btnSalvar.setOnClickListener(this);

        btnListar = findViewById(R.id.button9);
        btnListar.setOnClickListener(this);

        nome = findViewById(R.id.idnome);
        cpf = findViewById(R.id.idCPF);
        telefone = findViewById(R.id.idTelefone);
        cidade = findViewById(R.id.idCidade);
        idade = findViewById(R.id.idIdade);
        profissao = findViewById(R.id.idProfissao);
        bd = new BD(this, "banco", null, 1);

    }

    @Override
    public void onClick(View view) {
        if (view == btnSalvar) {
            String nome = this.nome.getText().toString();
            String cpf = this.cpf.getText().toString();
            String telefone = this.telefone.getText().toString();
            String cidade = this.cidade.getText().toString();
            String idade = this.idade.getText().toString();
            String profissao = this.profissao.getText().toString();

            Pessoa p = new Pessoa(nome, cpf, idade, profissao, cidade, telefone);
            bd.salvarDados(p);
            this.nome.setText("");
            this.cpf.setText("");
            this.telefone.setText("");
            this.idade.setText("");
            this.profissao.setText("");
            this.cidade.setText("");
            Toast.makeText(this, "Dados Salvos", Toast.LENGTH_SHORT).show();
        }
        if(view == btnListar){

            ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
            lista = bd.getLista();
            Pessoa p = new Pessoa();
            p.setNome(lista.get(0).getNome());
            p.setCpf(lista.get(0).getCpf());
            p.setCidade(lista.get(0).getCidade());
            p.setIdade(lista.get(0).getIdade());
            p.setProfissao(lista.get(0).getProfissao());
            p.setTelefone(lista.get(0).getTelefone());
            this.nome.setText(p.getNome());
            this.cpf.setText(p.getCpf());
            this.telefone.setText(p.getTelefone());
            this.idade.setText(p.getIdade());
            this.profissao.setText(p.getProfissao());
            this.cidade.setText(p.getCidade());
            Toast.makeText(this, "Dados Listados", Toast.LENGTH_SHORT).show();


        }


    }
    }