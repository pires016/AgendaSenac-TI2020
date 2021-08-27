package com.pires.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "senac.agenda.ti2020n";

    static final String CAMPO_NOME = "NOME";
    static final String CAMPO_DATA_NASCIMENTO = "DATA_NASCIMENTO";
    static final String CAMPO_TELEFONE = "TELEFONE";
    static final String CAMPO_ENDERECO = "ENDERECO";
    static final String CAMPO_ID = "ID";

    static final String TABELA_CONTATO = "CONATO";

    private final String BD_CREATE_CONTATO = "CREATE TABLE "+TABELA_CONTATO+" (" +
            CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPO_NOME + " TEXT," +
            CAMPO_TELEFONE + " TEXT," +
            CAMPO_ENDERECO + " TEXT," +
            CAMPO_DATA_NASCIMENTO  + " TEXT" +
            ");";

    public SQLiteHelper(@Nullable Context context) {
        super(context, NOME_BD, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD_CREATE_CONTATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
