package com.pires.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pires.agenda.dao.ContatoDAO;
import com.pires.agenda.model.Contato;

import java.text.ParseException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = findViewById(R.id.activity_adicionar_contato);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MainActivity.this, CadastrarContatoActivity.class);
                startActivity(intent);
            }
        }); }

    @Override
    protected void onResume() {
        super.onResume();

        ListView view = findViewById(R.id.activity_lista_contato);

        ContatoDAO contatoDAO = new ContatoDAO(MainActivity.this);

        try {
            view.setAdapter(new ArrayAdapter<Contato>(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    contatoDAO.listar()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}