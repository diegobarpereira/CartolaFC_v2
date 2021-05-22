package com.diegopereira.cartolafc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.partidas.ApiClient;
import com.diegopereira.cartolafc.partidas.Clubes;
import com.diegopereira.cartolafc.partidas.CounterHandler;
import com.diegopereira.cartolafc.partidas.Example;
import com.diegopereira.cartolafc.partidas.JogosRecyclerAdapter;
import com.diegopereira.cartolafc.partidas.Partida;
import com.diegopereira.cartolafc.partidas.RequestInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diegopereira.cartolafc.teste.RecyclerViewAdapter.SHARED_PREF_ID;

public class JogosActivity extends AppCompatActivity implements CounterHandler.CounterListener {

    RecyclerView recyclerView;
    List<Partida> list = new ArrayList<>();
    Map<Integer, Clubes> map = new HashMap();
    public static JogosRecyclerAdapter adapter;
    public static Integer rodadaatual;
    public static Integer rodada = MainActivity.rodada_;
    public static Integer rod;
    Button buttonPlus;
    Button buttonMinus;
    TextView tv_rodada;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogos);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh2);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rod = rodada;
                loadJogos(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

        recyclerView = findViewById(R.id.recyclerview_jogos);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        tv_rodada = (TextView) findViewById(R.id.tv_rodada);

        new CounterHandler.Builder()
                .incrementalView(buttonPlus)
                .decrementalView(buttonMinus)
                .minRange(1) // cant go any less than -50
                .maxRange(38) // cant go any further than 50
                .isCycle(false) // 49,50,-50,-49 and so on
                .counterDelay(200) // speed of counter
                .counterStep(1)  // steps e.g. 0,2,4,6...
                .listener(this) // to listen counter results and show them in app
                .build();


        loadJogos();


    }

    private void loadJogos() {
        SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);

        rod = preferences.getInt("sharedrod", rodada);
        System.out.println("ROD: " + rod);

        RequestInterface service = ApiClient.getInterface();
        Call<Example> call = service.getPartidas(String.valueOf(rod));

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                list = response.body().getPartidas();
                map = response.body().getClubes();
                rodadaatual = response.body().getRodada();

                Collections.sort(list, new Comparator<Partida>() {
                    @Override
                    public int compare(Partida o1, Partida o2) {
                        return o1.getPartidaData().compareTo(o2.getPartidaData());
                    }
                });

                adapter = new JogosRecyclerAdapter(JogosActivity.this, list, map);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

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

    @Override
    public void onIncrement( View view, int number) {
        if (number == rodada) {
            tv_rodada.setText("Rodada " + number + " (ATUAL)");
        }
        else {
            tv_rodada.setText("Rodada " + number);
        }

        System.out.println("num: " + number);
        SharedPreferences sharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("sharedrod", number);
        editor.apply();
        loadJogos();



    }

    @Override
    public void onDecrement(View view, int number) {
        if (number == rodada) {
            tv_rodada.setText("Rodada " + number + " (ATUAL)");
        }
        else {
            tv_rodada.setText("Rodada " + number);
        }

        System.out.println("num: " + number);
        SharedPreferences sharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("sharedrod", number);
        editor.apply();
        loadJogos();

    }
}



