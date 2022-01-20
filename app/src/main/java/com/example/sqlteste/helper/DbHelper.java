package com.example.sqlteste.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "MEU_BANCO_DE_DADOS";
    public static final int VERSION = 1;
    public static final String NOME_TABLE = "Tarefas";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + NOME_TABLE +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " nome TEXT NOT NULL); ";

        try {
            db.execSQL(sql);
            Log.i("TAG", "Sucesso ao criar a tabela");

        }catch (Exception e){
            Log.i("TAG", "Erro ao criar a tabela");
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+ NOME_TABLE + " ;" ;

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("TAG", "Sucesso ao Atualizar a tabela");

        }catch (Exception e){
            Log.i("TAG", "Erro ao Atualizar a tabela");
            e.printStackTrace();
        }
    }
}
