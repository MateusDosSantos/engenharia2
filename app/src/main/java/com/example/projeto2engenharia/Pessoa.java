package com.example.projeto2engenharia;

public class Pessoa {

    private String nome, cpf, idade, profissao, cidade, telefone;

    public Pessoa(){

    }
    public Pessoa(String nome, String cpf, String idade, String profissao, String cidade, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.profissao = profissao;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public Pessoa(String telefone) {
        this.telefone = telefone;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
