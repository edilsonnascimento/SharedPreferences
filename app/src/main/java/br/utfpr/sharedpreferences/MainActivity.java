package br.utfpr.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private static final String ARQUIVO = "br.edu.utfpr.edilsondonascimento.sharedpreferences.PREFERENCIAS_USUARIO";
    private static final String COR = "COR";
    private int opcao = Color.BLUE;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layoutMain);
        lerPreferenciasUsuario();
    }

    private void lerPreferenciasUsuario(){

        //Criar arquivo caso não existe caso existe abre o arquivo
        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        //Ler arquivo, pega o valor da chave(COR), caso não tenha pega valor de opcao
        opcao = shared.getInt(COR,opcao);

        mudaCorFundo();

    }

    private void mudaCorFundo(){
        layout.setBackgroundColor(opcao);
    }

    private void salvarPreferenciaUsuario(int novoValor){

        //Criar arquivo caso não existe caso existe abre o arquivo
        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        //Abre arquivo para edicao, armazena em editor
        SharedPreferences.Editor editor = shared.edit();

        //altera valor
        editor.putInt(COR, novoValor);

        //salva no arquivo
        editor.commit();

        opcao = novoValor;
        mudaCorFundo();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;
        switch (opcao){
            case Color.YELLOW:
                item = menu.findItem(R.id.menuItemAmarelo);
                break;
            case Color.BLUE:
                item = menu.findItem(R.id.menuItemAzul);
                break;

            case Color.RED:
                item = menu.findItem(R.id.menuItemVermelho);
                break;

            default:
                return false;
        }

        item.setChecked(true);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuItemAmarelo:
                salvarPreferenciaUsuario(Color.YELLOW);
                return true;
            case R.id.menuItemAzul:
                salvarPreferenciaUsuario(Color.BLUE);
                return true;
            case R.id.menuItemVermelho:
                salvarPreferenciaUsuario(Color.RED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}