package com.example.sqlteste.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqlteste.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);

        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNomeTarefa());

        try {

            Log.i("TAG", "salva com sucesso: ");
            escreve.insert(DbHelper.NOME_TABLE,null,cv);
        }catch (Exception e){
            Log.i("TAG", "Erro ao salvar tarefa ");
            e.printStackTrace();
            return false;
        }


        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNomeTarefa());


        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.NOME_TABLE,cv,"id=?",args);
            Log.i("TAG", "Atualicada com sucesso: ");
        }catch (Exception e){
            Log.i("TAG", "Erro ao Atualizar tarefa ");
            e.printStackTrace();
            return false;
        }


        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {


        try {


            String[] args = {tarefa.getId().toString()};
            escreve.delete(DbHelper.NOME_TABLE,"id=?",args);
            Log.i("TAG", "Tarefa Removida com Sucesso: ");
        }catch (Exception e){
            Log.i("TAG", "Erro ao Remover Tarefa ");
            e.printStackTrace();
            return false;
        }



        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.NOME_TABLE + " ;";
        Cursor cursor = le.rawQuery(sql,null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();

             Long id = cursor.getLong(cursor.getColumnIndex("id"));
             String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));


            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);;

            tarefas.add(tarefa);
        }

        return tarefas;

    }
}
