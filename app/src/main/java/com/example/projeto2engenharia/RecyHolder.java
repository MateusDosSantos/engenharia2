package com.example.projeto2engenharia;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyHolder extends RecyclerView.ViewHolder {
    protected TextView t_nome, t_cpf, t_cidade, t_idade, t_profissao, t_telefone;
    public RecyHolder(@NonNull View itemView) {
        super(itemView);
        t_nome = itemView.findViewById(R.id.t_nome);
        t_cpf = itemView.findViewById(R.id.t_cpf);
        t_cidade = itemView.findViewById(R.id.t_cidade);
        t_idade = itemView.findViewById(R.id.t_idade);
        t_profissao = itemView.findViewById(R.id.t_profissa);
        t_telefone = itemView.findViewById(R.id.t_telefone);
    }
}
