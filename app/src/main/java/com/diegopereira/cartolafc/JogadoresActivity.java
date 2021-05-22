package com.diegopereira.cartolafc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.jogadores.APIInterface;
import com.diegopereira.cartolafc.jogadores.ApiClient;
import com.diegopereira.cartolafc.jogadores.Atleta;
import com.diegopereira.cartolafc.jogadores.Clubes;
import com.diegopereira.cartolafc.jogadores.Jogador;
import com.diegopereira.cartolafc.jogadores.JogadoresRecyclerAdapter;
import com.diegopereira.cartolafc.jogadores.Posicoes;
import com.diegopereira.cartolafc.jogadores.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JogadoresActivity extends AppCompatActivity {

    RecyclerView rv_jogadores;
    List<Atleta> list = new ArrayList<>();
    Map<Integer, Clubes> map = new HashMap();
    Map<Integer, Posicoes> posicoes = new HashMap();
    Map<Integer, Status> status = new HashMap();
    JogadoresRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadores);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        rv_jogadores = findViewById(R.id.rv_jogadores);
        rv_jogadores.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_jogadores.setLayoutManager(linearLayoutManager);

        getJogador();

    }

    private void getJogador() {
        APIInterface service = ApiClient.getInterface();
        Call<Jogador> call = service.getAtletas();

        call.enqueue(new Callback<Jogador>() {
            @Override
            public void onResponse(Call<Jogador> call, Response<Jogador> response) {
                list = response.body().getAtletas();
                map = response.body().getClubes();
                posicoes = response.body().getPosicoes();
                status = response.body().getStatus();

                Collections.sort(list, new Comparator<Atleta>() {
                    @Override
                    public int compare(Atleta o1, Atleta o2) {
                        return o2.getMediaNum().compareTo(o1.getMediaNum());
                    }
                });
                adapter = new JogadoresRecyclerAdapter(getApplicationContext(), list, map, posicoes, status);
                rv_jogadores.setAdapter(adapter);

                adapter.notifyDataSetChanged();
                //Log.d("TAG", String.valueOf(response.body().getPosicoes()));

            }

            @Override
            public void onFailure(Call<Jogador> call, Throwable t) {
                Log.e("TAG2", String.valueOf(t.getMessage()));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
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


        return super.onOptionsItemSelected(item);
    }
}
