package com.example.projeto2engenharia;

import android.annotation.SuppressLint;
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
    private Button btnSalvar, btnListar, btnAtualizarTelefone, btnDeletar;
    private BD bd;
    private ArrayList<Pessoa> lista;
    private int indiceLista;
    private ImageButton antes, depois;
    private TextView placar;
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

        lista = new ArrayList<Pessoa>();
        indiceLista = 0;

        antes = findViewById(R.id.imageButton3);
        antes.setEnabled(false);
        antes.setOnClickListener(this);

        depois = findViewById(R.id.imageButton4);
        depois.setEnabled(false);
        depois.setOnClickListener(this);

        placar = findViewById(R.id.textView2);

        btnSalvar = findViewById(R.id.salvar);
        btnSalvar.setOnClickListener(this);

        btnDeletar = findViewById(R.id.button10);
        btnDeletar.setOnClickListener(this);


        btnAtualizarTelefone = findViewById(R.id.button8);
        btnAtualizarTelefone.setOnClickListener(this);

        btnListar = findViewById(R.id.button9);
        btnListar.setOnClickListener(this);

        nome = findViewById(R.id.idnome);
        cpf = findViewById(R.id.idCPF);
        cpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String cpfModificado = cpf.getText().toString().replaceAll("[^\\d]", "");
                    if(cpfModificado.length() >=11){
                        cpfModificado = cpfModificado.substring(0,3)+"."+cpfModificado.substring(3,6)+"."+cpfModificado.substring(6,9)+"-"+cpfModificado.substring(9);
                        cpf.setText(cpfModificado);
                    }

                }
            }
        });
        telefone = findViewById(R.id.idTelefone);
        telefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String telModificado = telefone.getText().toString().replaceAll("[^\\d]", "");
                    if(telModificado.length() >=10){
                        Log.d("##", "dentro do IF");
                        telModificado = "(" +telModificado.substring(0,2)+")"+telModificado.substring(2);
                        telefone.setText(telModificado);
                   }
                }

            }
        });






        cidade = findViewById(R.id.idCidade);
        idade = findViewById(R.id.idIdade);
        profissao = findViewById(R.id.idProfissao);
        bd = new BD(this, "banco", null, 1);

    }
    public void atualizaPLacar(){
        int x = indiceLista;
        x++;
        placar.setText("Registro "+Integer.toString(x)+" de "+Integer.toString(lista.size()));
    }
    public void carregaDadosInterface(){
        this.nome.setText(lista.get(indiceLista).getNome());
        this.cpf.setText(lista.get(indiceLista).getCpf());
        this.telefone.setText(lista.get(indiceLista).getTelefone());
        this.idade.setText(lista.get(indiceLista).getIdade());
        this.profissao.setText(lista.get(indiceLista).getProfissao());
        this.cidade.setText(lista.get(indiceLista).getCidade());
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
            lista = bd.getLista();
            if(!lista.isEmpty()){
                this.nome.setEnabled(false);
                this.cpf.setEnabled(false);
                this.idade.setEnabled(false);
                this.cidade.setEnabled(false);
                this.profissao.setEnabled(false);
                antes.setEnabled(true);
                depois.setEnabled(true);
                indiceLista= 0;
                carregaDadosInterface();
                atualizaPLacar();
                Toast.makeText(this, "Dados Listados", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == antes){
            indiceLista--;
            if(indiceLista<0){
                indiceLista = lista.size()-1;
            }
            carregaDadosInterface();
            atualizaPLacar();
        }
        if(view == depois){
            indiceLista++;
            if(indiceLista >= lista.size()){
                indiceLista=0;
            }
            carregaDadosInterface();
            atualizaPLacar();
        }
        if(view == btnAtualizarTelefone){
            String cpf = this.cpf.getText().toString();
            String novoTelefone = this.telefone.getText().toString();
            bd.atualizarTelefone(cpf, novoTelefone);
            lista = null;
            lista = bd.getLista();
            indiceLista = 0;
            carregaDadosInterface();
            atualizaPLacar();
            Toast.makeText(this, "Telefone Atualizado", Toast.LENGTH_SHORT).show();
        }
        if(view == btnDeletar){
            String cpf = this.cpf.getText().toString();
            bd.deletarRegistro(cpf);
            lista = null;
            lista = bd.getLista();
            indiceLista = 0;
            carregaDadosInterface();
            atualizaPLacar();
            Toast.makeText(this, "Registro Deletado", Toast.LENGTH_SHORT).show();

        }


    }
    }