package com.diegopereira.cartolafc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.diegopereira.cartolafc.status.APIInterface;
import com.diegopereira.cartolafc.status.Status;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private TextView tv_rodada, tv_status, tv_times_escalados, tv_fechamento;
    public static final String SHAREDMAIN_PREF_NAME = "SHAREDMAIN";
    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String MAIN_SHARED_PREF = "main";

    public static String rodada;
    public static Integer rodada_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh3);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadstatus(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });


        tv_rodada = (TextView) findViewById(R.id.tv_rodada);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_times_escalados = (TextView) findViewById(R.id.tv_times_escalados);
        tv_fechamento = (TextView) findViewById(R.id.tv_fechamento);

        loadstatus();



    }

    private void loadstatus() {
        APIInterface status = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<Status> call = status.getStatus();

        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {

                    assert response.body() != null;
                    tv_rodada.setText(String.valueOf(response.body().getRodadaAtual()));

                    Integer status = response.body().getStatusMercado();
                    if (String.valueOf(status).equals("1")) {
                        tv_status.setText("Mercado Aberto");
                    }
                    if (String.valueOf(status).equals("2")) {
                        tv_status.setText("Mercado Fechado");
                    }
                    if (String.valueOf(status).equals("3")) {
                        tv_status.setText("Mercado em Atualização");
                    }
                    if (String.valueOf(status).equals("4")) {
                        tv_status.setText("Mercado em Manutenção");
                    }
                    if (String.valueOf(status).equals("6")) {
                        tv_status.setText("Final de Temporada");
                    }
                    Log.d("TAG", String.valueOf(status));
                    SharedPreferences preferences = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString(MAIN_SHARED_PREF, String.valueOf(status));
                    rodada = "\"" + String.valueOf(response.body().getRodadaAtual() + "\""); //
                    rodada_ = response.body().getRodadaAtual(); //

                    System.out.println("Rodada: " + rodada);

                    editor.apply();

                    tv_times_escalados.setText(String.valueOf(response.body().getTimesEscalados()));
                    Integer minutos = response.body().getFechamento().getMinuto();
                    if (minutos < 10) {
                        tv_fechamento.setText(String.valueOf(response.body().getFechamento().getDia()) + "/" + String.valueOf(response.body().getFechamento().getMes()) + "/" + String.valueOf(response.body().getFechamento().getAno()) + " - " + String.valueOf(response.body().getFechamento().getHora() + ":" + String.valueOf(minutos).replace("0", "00")));
                    } else {
                        tv_fechamento.setText(String.valueOf(response.body().getFechamento().getDia()) + "/" + String.valueOf(response.body().getFechamento().getMes()) + "/" + String.valueOf(response.body().getFechamento().getAno()) + " - " + String.valueOf(response.body().getFechamento().getHora() + ":" + String.valueOf(minutos)));

                    }

                    Log.d("TAG2", String.valueOf(response.body().getFechamento().getDia()) + "/" + String.valueOf(response.body().getFechamento().getMes()) + "/" + String.valueOf(response.body().getFechamento().getAno()) + " - " + String.valueOf(response.body().getFechamento().getHora() + ":" + String.valueOf(response.body().getFechamento().getMinuto()).replace("0", "00")));
                }
            }
            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();

        if (id == R.id.menu_mais) {
            Intent intent = new Intent(getApplicationContext(), MaisEscalados.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.partidas) {
            Intent intent = new Intent(getApplicationContext(), JogosActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.jogadores) {
            Intent intent = new Intent(getApplicationContext(), JogadoresActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.parciais) {
            Intent intent = new Intent(getApplicationContext(), ParciaisActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.liga) {
            Intent intent = new Intent(getApplicationContext(), Teste2Activity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.classi) {
            Intent intent = new Intent(getApplicationContext(), ClassificacaoActivity.class);
            startActivity(intent);
            return true;
        }
        /*if (id == R.id.login) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            return true;
        } */
        if (id == R.id.login) {
            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.ligaauth) {
            Intent intent = new Intent(getApplicationContext(), LigaAuthActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.groups) {
            Intent intent = new Intent(getApplicationContext(), FavoritosActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}