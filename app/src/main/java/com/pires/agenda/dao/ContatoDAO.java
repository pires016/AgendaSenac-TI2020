package com.pires.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pires.agenda.model.Contato;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private static ArrayList<Contato> contatos = new ArrayList<Contato>();

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase db;

    public ContatoDAO(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void salvar(Contato contato) {
        db = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.CAMPO_NOME, contato.getNome());
        values.put(SQLiteHelper.CAMPO_TELEFONE, contato.getTelefone());
        values.put(SQLiteHelper.CAMPO_ENDERECO, contato.getEndereco());
        values.put(SQLiteHelper.CAMPO_DATA_NASCIMENTO, contato.getDataNascimento() != null ? contato.getDataNascimento().toString() : null);

        db.insert(SQLiteHelper.TABELA_CONTATO, null, values);

        db.close();
    }

    public List<Contato> listar() throws ParseException {
        db = sqLiteHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLiteHelper.TABELA_CONTATO + ";";

        Cursor c = db.rawQuery(sql, null);

        List<Contato> contatos = new ArrayList<>();

        while(c.moveToNext()){
            Contato contato = new Contato();
            contato.setId(c.getInt(c.getColumnIndex(SQLiteHelper.CAMPO_ID)));
            contato.setNome(c.getString(c.getColumnIndex(SQLiteHelper.CAMPO_NOME)));
            contato.setTelefone(c.getString(c.getColumnIndex(SQLiteHelper.CAMPO_TELEFONE)));
            contato.setEndereco(c.getString(c.getColumnIndex(SQLiteHelper.CAMPO_ENDERECO)));
            if(c.getString(c.getColumnIndex(SQLiteHelper.CAMPO_DATA_NASCIMENTO)) != null &&
                    !c.getString(c.getColumnIndex(SQLiteHelper.CAMPO_DATA_NASCIMENTO)).isEmpty()) {
                contato.setDataNascimento(
                        new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(SQLiteHelper.CAMPO_DATA_NASCIMENTO)))
                );
            }
            contatos.add(contato);
        }

        c.close();
        db.close();

        return contatos;
    }
}
