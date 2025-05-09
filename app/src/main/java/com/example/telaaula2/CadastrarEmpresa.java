package com.example.telaaula2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastrarEmpresa extends AppCompatActivity {

    private EditText mInputNomeFantasia;
    private Button  mButtonCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.interface_cadastrar_empresa);

        mInputNomeFantasia = findViewById(R.id.IdInputEmpresa);
        mButtonCadastrar = findViewById(R.id.IdButtonCadastrarActivity2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mButtonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeFantasia = mInputNomeFantasia.getText().toString();

                if (!nomeFantasia.isEmpty()){
                    getSharedPreferences(MainActivity.ARQUIVO_DATA_BASE, Context.MODE_PRIVATE).edit().putString("nomeFantasia", nomeFantasia).apply();
                } else Toast.makeText(CadastrarEmpresa.this, "Nome fantasia não pode ser vazio!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}