package com.pires.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pires.agenda.dao.ContatoDAO;
import com.pires.agenda.model.Contato;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CadastrarContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_contato);

        Button cadastrar = findViewById(R.id.activity_botao_cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = new Contato();

                EditText editTextNome = findViewById(R.id.activity_nome_contato);
                EditText editTextTelefone = findViewById(R.id.activity_telefone);
                EditText editTextEndereco = findViewById(R.id.activity_endereco);
                EditText editTextDataNascimento = findViewById(R.id.activity_data_nascimento);

                if (editTextNome != null &&                                             // if = se
                        editTextNome.getText() != null &&                               //!= significa q é diferente de:
                        !editTextNome.getText().toString().equals("")) {
                    contato.setNome(editTextNome.getText().toString());
                    contato.setTelefone(editTextTelefone.getText().toString());
                    contato.setEndereco((editTextEndereco.getText().toString()));
                    if (editTextDataNascimento != null &&                            // Exemplo dessa linha: Se DataNascimento for diferente de nulo
                            editTextDataNascimento.getText() != null &&
                            !editTextDataNascimento.getText().toString().equals("")) {
                        try {
                            contato.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(editTextDataNascimento.getText().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    ContatoDAO contatoDAO = new ContatoDAO(CadastrarContatoActivity.this);
                    contatoDAO.salvar(contato);

                   // startActivity(new Intent(CadastrarContatoActivity.this, MainActivity.class));
                    finish(); // finish resumindo serve para parar o comando da tela aqui, se clicar no botao de voltar do celular, ele vai fecha o app

                } else {

                    Toast.makeText(CadastrarContatoActivity.this, "O nome precisa ser preenchido", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
