package com.example.projeto2engenharia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
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
    private Button btnSalvar, btnListar, btnAtualizar, btnDeletar, btnRecycler;
    private BD bd;
    private ImageButton antes, depois;
    private ArrayList<Pessoa> listap;
    private TextView text;
    private int indiceLista;

//lista ficou global
// uso de um inteiro para o indice da lista
//testar se a lista esta vazia quando retorna
//testar o indicce da lista no avanço e retrocesso.
//criar metodo para plotar dados

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

        btnRecycler = findViewById(R.id.button7);
        btnRecycler.setOnClickListener(this);

        btnListar = findViewById(R.id.button9);
        btnListar.setOnClickListener(this);

        btnDeletar = findViewById(R.id.button10);
        btnDeletar.setOnClickListener(this);

        btnAtualizar = findViewById(R.id.button8);
        btnAtualizar.setOnClickListener(this);

        antes = findViewById(R.id.imageButton3);
        antes.setOnClickListener(this);
        depois = findViewById(R.id.imageButton4);
        depois.setOnClickListener(this);

        nome = findViewById(R.id.idnome);
        cpf = findViewById(R.id.idCPF);
        cpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){

                    String cpf_modificado = cpf.getText().toString()
                            .replaceAll("[^\\d]", "");

                    if(cpf_modificado.length() == 11){

                        cpf_modificado = cpf_modificado.substring(0,3) + "." +
                                cpf_modificado.substring(3,6) + "." +
                                cpf_modificado.substring(6,9) + "-" +
                                cpf_modificado.substring(9);

                        cpf.setText(cpf_modificado);
                    }
                }
            }
        });


        telefone = findViewById(R.id.idTelefone);
        telefone. setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String tel = telefone.getText().toString()
                            .replaceAll("[^\\d]", "");
                    if(tel.length() >= 10) {
                        tel = "(" + tel.substring(0, 2) + ") " +
                                tel.substring(2);
                        telefone.setText(tel);
                    }
            }}
        });
        cidade = findViewById(R.id.idCidade);
        idade = findViewById(R.id.idIdade);
        profissao = findViewById(R.id.idProfissao);
        bd = new BD(this, "banco", null, 1);
        listap = new ArrayList<Pessoa>();
        indiceLista = 0;

        text = findViewById(R.id.textView2);
        text.setText("");

    }
    public void carregaDadosInterface(){
        this.nome.setText(listap.get(indiceLista).getNome());
        this.cpf.setText(listap.get(indiceLista).getCpf());
        this.telefone.setText(listap.get(indiceLista).getTelefone());
        this.idade.setText(listap.get(indiceLista).getIdade());
        this.profissao.setText(listap.get(indiceLista).getProfissao());
        this.cidade.setText(listap.get(indiceLista).getCidade());
    }
    public void atualizaPlacar(){
        int x = indiceLista;
        x++;
        text.setText("Registro "+Integer.toString(x)+" de "+Integer.toString(listap.size()));
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
            nome.setEnabled(false);
            cpf.setEnabled(false);
            cidade.setEnabled(false);
            idade.setEnabled(false);
            profissao.setEnabled(false);

            listap = bd.getLista();
            indiceLista = 0;
            if(!listap.isEmpty()) {
                atualizaPlacar();
               carregaDadosInterface();
                Toast.makeText(this, "Dados Listados", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Não há dados para exibir", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == antes){
            indiceLista--;
            if(indiceLista<0)
            {
                indiceLista = listap.size()-1;
                atualizaPlacar();
                carregaDadosInterface();
            }
            else {
                carregaDadosInterface();
                atualizaPlacar();
            }
        }
        if(view == depois){
            indiceLista++;
            if(indiceLista>=listap.size())
            {
                indiceLista = 0;
                atualizaPlacar();
                carregaDadosInterface();
            }
            else {
                atualizaPlacar();
                carregaDadosInterface();
            }
        }
        if(view == btnAtualizar){
            bd.atualizaTelefone(cpf.getText().toString(), telefone.getText().toString());
            listap = null;
            listap = bd.getLista();
            Toast.makeText(this, "Telefone Atualizado", Toast.LENGTH_SHORT).show();
        }
        if(view ==btnDeletar){
            bd.deletar(cpf.getText().toString());
            listap = null;
            listap = bd.getLista();
            Toast.makeText(this, "Registro Deletado", Toast.LENGTH_SHORT).show();
            indiceLista = 0;
            carregaDadosInterface();
            atualizaPlacar();
        }
        if(view == btnRecycler){
            startActivity(new Intent(this, TelaRecycler.class));
        }
    }
    }