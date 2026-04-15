package com.example.projeto2engenharia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "banco";

    public BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS pessoa (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT,"+
                        "cpf TEXT UNIQUE,"+
                        "idade TEXT,"+
                        "profissao TEXT,"+
                        "telefone TEXT, "+
                        "cidade TEXT"+
                        ")"
        );
        Log.i("##", "Tabela Pessoa Criada");
    }
    public void salvarDados(Pessoa p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", p.getNome());
        valores.put("cpf", p.getCpf());
        valores.put("idade", p.getIdade());
        valores.put("profissao", p.getProfissao());
        valores.put("telefone", p.getTelefone());
        valores.put("cidade", p.getCidade());
        db.insert("pessoa", null, valores);
        db.close();
        Log.i("##", "Dados Salvos");
    }
    public ArrayList<Pessoa> getLista(){
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();

        SQLiteDatabase db = getReadableDatabase();
         Cursor cursor = db.query(
                 "pessoa",null,null,null,
                 null,null,null);
         if(cursor.moveToFirst()){
             do{
                 String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                 String cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"));
                 String idade = cursor.getString(cursor.getColumnIndexOrThrow("idade"));
                 String profissao = cursor.getString(cursor.getColumnIndexOrThrow("profissao"));
                 String telefone = cursor.getString(cursor.getColumnIndexOrThrow("telefone"));
                 String cidade = cursor.getString(cursor.getColumnIndexOrThrow("cidade"));
                 Pessoa p = new Pessoa(nome, cpf, idade, profissao, cidade, telefone);
                 lista.add(p);
             }while(cursor.moveToNext());
         }
         cursor.close();
         db.close();
         for(int i = 0; i<lista.size();i++){
             Log.i("##", lista.get(i).getNome());
             Log.i("##", lista.get(i).getCpf());
             Log.i("##", lista.get(i).getCidade());
             Log.i("##", lista.get(i).getIdade());
             Log.i("##", lista.get(i).getProfissao());
             Log.i("##", lista.get(i).getTelefone());
             Log.i("##", "----------");
         }

      return lista;
    }
    public boolean atualizaTelefone(String cpf, String novo_telefone){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("telefone", novo_telefone);
        db.update("pessoa", valores, "cpf=?", new String[]{cpf});
        db.close();
        Log.i("##", "Telefone Atualizado");
        return true;
    }
    public void deletar(String cpf){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("pessoa", "cpf=?", new String[]{cpf});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
