package com.example.projeto2engenharia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<RecyHolder>  {
    private ArrayList<Pessoa> lista;
    private Context context;
    private OnItemClick listener;
    @NonNull
    @Override
    public RecyHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_recycler, parent, false);
        return new RecyHolder(view);
    }
    public Adaptador(ArrayList<Pessoa> lista, OnItemClick listener){

        this.lista = lista;
        this.listener = listener;
    }
    public interface OnItemClick {
        void onClick(Pessoa p);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyHolder holder, int position) {
            Pessoa p = new Pessoa();
            holder.t_nome.setText(lista.get(position).getNome());
            holder.t_cpf.setText(lista.get(position).getCpf());
            holder.t_cidade.setText(lista.get(position).getCidade());
            holder.t_idade.setText(lista.get(position).getIdade());
            holder.t_profissao.setText(lista.get(position).getProfissao());
            holder.t_telefone.setText(lista.get(position).getTelefone());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onClick(p);
                }
            });
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }
}
