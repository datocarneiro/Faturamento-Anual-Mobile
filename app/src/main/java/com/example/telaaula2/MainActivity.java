package com.example.telaaula2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;

import java.util.IdentityHashMap;

public class MainActivity extends AppCompatActivity {
    // criando variaveis de instancia
    // tipo de encapsulamento, o tipo, e a variavel

    public static final String ARQUIVO_DATA_BASE = "MeusDados";
    private NumberPicker mNumberPicker;
    private RadioGroup mGrupoRadio;
    private EditText mInputvalor;
    private TextView mTitulo;
    private TextView mValorSaldo;
    private Button mButtonConfirmar;
    private Button mButtonAddTitulo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mTitulo = findViewById(R.id.IdTitulo);
        mNumberPicker = findViewById(R.id.IdAno);
        mNumberPicker.setMinValue(2000);
        mNumberPicker.setMaxValue(2025);

        mButtonConfirmar = findViewById(R.id.IdButtonConfirmar);
        mButtonAddTitulo = findViewById(R.id.IdButtonAddTitulo);
        mGrupoRadio = findViewById(R.id.IdGrupoRadio);
        mInputvalor = findViewById(R.id.IdInputValor);
        mValorSaldo = findViewById(R.id.IdSaldo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                exibirSaldo(newVal);
            }
        });

        mButtonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mInputvalor.getText().toString().isEmpty()) {
                    float valorDigitado = Float.parseFloat(mInputvalor.getText().toString());

                    int ano = mNumberPicker.getValue();

                    int acaoEscolhida = mGrupoRadio.getCheckedRadioButtonId();

                    if (acaoEscolhida == R.id.IdAdicionar) {
                        adicionarValor(ano, valorDigitado);
                    } else {
                        excluirValor(ano, valorDigitado);
                    }
                    exibirSaldo(ano);
                }
            }
        });

        mButtonAddTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CadastrarEmpresa.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences dataBase = getSharedPreferences(ARQUIVO_DATA_BASE,Context.MODE_PRIVATE);
        String nomeFantasia = dataBase.getString("nomeFantasia", null);
        Log.d("DEBUG", "nomeFantasia: " + nomeFantasia);
        if (nomeFantasia != null){
            mTitulo.setText(nomeFantasia);
        }
        int ano = mNumberPicker.getValue();
        exibirSaldo(ano);
    }

    private void adicionarValor(int ano, float valorDigitado) {
        SharedPreferences dados = getSharedPreferences(ARQUIVO_DATA_BASE, Context.MODE_PRIVATE);
        float saldoAtual = dados.getFloat(String.valueOf(ano), 0);
        float saldoTotal = saldoAtual + valorDigitado;
        dados.edit().putFloat(String.valueOf(ano), saldoTotal).apply();
    }

    private void excluirValor(int ano, float valorDigitado) {
        SharedPreferences dados = getSharedPreferences(ARQUIVO_DATA_BASE, Context.MODE_PRIVATE);
        float saldoAtual = dados.getFloat(String.valueOf(ano), 0);
        float saldoTotal = saldoAtual - valorDigitado;
        if (saldoTotal < 0) {
            saldoTotal = 0;
        }
        dados.edit().putFloat(String.valueOf(ano), saldoTotal).apply();
    }

    private void exibirSaldo(int ano) {
        SharedPreferences dados = getSharedPreferences(ARQUIVO_DATA_BASE, Context.MODE_PRIVATE);
        float saldoTotal = dados.getFloat(String.valueOf(ano), 0);
        mValorSaldo.setText(String.format("R$ %.2f", saldoTotal));
    }
}
