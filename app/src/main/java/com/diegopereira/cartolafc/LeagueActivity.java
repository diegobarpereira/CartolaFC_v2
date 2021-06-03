package com.diegopereira.cartolafc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.league.APIInterface;
import com.diegopereira.cartolafc.league.DatabaseHelper;
import com.diegopereira.cartolafc.league.League;
import com.diegopereira.cartolafc.league.MyParcialSection;
import com.diegopereira.cartolafc.league.MySection;
import com.diegopereira.cartolafc.league.ServiceGenerator;
import com.diegopereira.cartolafc.league.TimePontos;
import com.diegopereira.cartolafc.league.Times;
import com.diegopereira.cartolafc.liga.Atleta;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.diegopereira.cartolafc.parciais.Clubes;
import com.diegopereira.cartolafc.parciais.Posicoes;
import com.diegopereira.cartolafc.teste.Players;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;
import static com.diegopereira.cartolafc.ligaauth.MySection.SHARED_PREF_SLUG;
import static com.diegopereira.cartolafc.ligaauth.MySection.SLUG_SHARED_PREF;
import static com.diegopereira.cartolafc.ligaauth.MySection.NAME_SHARED_PREF;


import static com.diegopereira.cartolafc.WebViewActivity.SHARED_PREF_NAME;
import static com.diegopereira.cartolafc.WebViewActivity.SHARED_TOKEN;


public class LeagueActivity extends AppCompatActivity implements ServiceConnection {
    private ChamadaMetodo s;

    SharedPreferences preferences, token_preferences;
    String slug;
    RecyclerView recyclerView;
    public static List<Times> list = new ArrayList<>();
    List<Map.Entry<String, Atletas>> wordList = new ArrayList<Map.Entry<String, Atletas>>();

    Set<Map.Entry<String, Atletas>> set;
    List<Map.Entry<String, Atletas>> list2;
    public static List<com.diegopereira.cartolafc.teste.Atletas> atletas = new ArrayList<>();;
    Map<Integer, Posicoes> posicoes = new HashMap();
    Map<Integer, Clubes> clubes = new HashMap();

    Map<String, Double> mapparciais = new HashMap<>(); //
    public static ArrayList<TimePontos> teste = new ArrayList<>();

    public static SectionedRecyclerViewAdapter sectionAdapter;

    private DatabaseHelper database;

    Integer times_id;
    TextView total, ultima;
    String atleta_ids;

    Double pontos;

    String nome;
    String token = "";
    String stat;

    private ProgressBar loadProgress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefreshLeague);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (stat.equals("1") || stat.equals("6")) {
                    sectionAdapter.removeAllSections();
                    sectionAdapter.notifyDataSetChanged();
                    loadLeague();
                }
                if (stat.equals("2")) {
                    //teste.clear();

                    loadParciais();

                    //database.delete();

                }
                pullToRefresh.setRefreshing(false);
            }
        });

        sectionAdapter = new SectionedRecyclerViewAdapter();

        SharedPreferences sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");
        //stat = "2";


        System.out.println(stat);


        token_preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        token = token_preferences.getString(SHARED_TOKEN, "");
        preferences = getSharedPreferences(SHARED_PREF_SLUG, MODE_PRIVATE);
        slug = preferences.getString(SLUG_SHARED_PREF, "");
        nome = preferences.getString(NAME_SHARED_PREF, "");
        System.out.println(slug);
        //Toast.makeText(getApplicationContext(), slug + " Activity", Toast.LENGTH_SHORT).show();

        loadProgress = (ProgressBar) findViewById(R.id.leagueprogressBar);
        recyclerView = findViewById(R.id.rv_league);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        if (stat.equals("1") || stat.equals("6")) {

            loadLeague();

        }
        if (stat.equals("2")) {


            Handler handler = new Handler();
            long delay = 2000; // tempo de delay em millisegundos
            handler.postDelayed(new Runnable() {
                public void run() {

                    loadParciais();
//                    MyParcialSection section2 = new MyParcialSection(getApplicationContext(), "Teste", list, atletas, wordList);
//                    sectionAdapter.addSection(section2);
//                    recyclerView.setAdapter(sectionAdapter);
                }
            }, delay);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, ChamadaMetodo.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    private void loadLeague() {
        APIInterface league = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<League> call = league.getTime(token, slug);
        call.enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                loadProgress.setVisibility(View.GONE);

                System.out.println(response.body().getTimes());
                list = response.body().getTimes();

                //sortTotalDESC();

                com.diegopereira.cartolafc.league.MySection section1 = new MySection(getApplicationContext(), nome, list);
                sectionAdapter.addSection(section1);
                //sectionAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(sectionAdapter);

            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {

            }
        });
    }

    private void loadParciais() {

        Intent service = new Intent(getApplicationContext(), ChamadaMetodo.class);
        getApplicationContext().startService(service);

        //System.out.println(s.getWordList().toString());
        if (s != null) {
            Toast.makeText(getApplicationContext(), "Number of elements: " + s.getWordList().size(),
                    Toast.LENGTH_SHORT).show();
            wordList.clear();
            wordList.addAll(s.getWordList());

        } else {
            System.out.println(s.getWordList().toString());
        }


        APIInterface league = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<League> call = league.getTime(token, slug);
        call.enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                loadProgress.setVisibility(View.GONE);

                System.out.println(response.body().getTimes());
                list = response.body().getTimes();

                com.diegopereira.cartolafc.teste.APIInterface status = com.diegopereira.cartolafc.teste.ServiceGenerator.getRetrofit().create(com.diegopereira.cartolafc.teste.APIInterface.class);

                for (int i = 0; i < list.size(); i++) {
                    times_id = list.get(i).getTime_id();
                    Call<Players> call_players = status.getTime(times_id.toString());

                    call_players.enqueue(new Callback<Players>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(Call<Players> call_players, Response<Players> response) {
                            //if (response.isSuccessful()) {
                            response.code();
                            atletas = response.body().getAtletas();
                            MyParcialSection section2 = new MyParcialSection(getApplicationContext(), "Teste", list, atletas, wordList);
                            sectionAdapter.addSection(section2);
                            recyclerView.setAdapter(sectionAdapter);
                            sectionAdapter.notifyDataSetChanged();


                            //}
                        }

                        @Override
                        public void onFailure(Call<Players> call, Throwable t) {
                            Log.e("ERROR", t.getMessage());
                        }
                    });


                }





            }


            @Override
            public void onFailure(Call<League> call, Throwable t) {

            }


        });




    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        ChamadaMetodo.MyBinder b = (ChamadaMetodo.MyBinder) binder;
        s = b.getService();
        Toast.makeText(LeagueActivity.this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        s = null;
    }

    public static void sortTotalDESC() {

        Collections.sort(list, new Comparator<Times>() {
            @Override
            public int compare(Times o1, Times o2) {
                return (o1.getRanking().getCampeonato()).compareTo(o2.getRanking().getCampeonato());
            }
        });
        sectionAdapter.notifyDataSetChanged();

    }


    public static void sortTotalASC() {

        Collections.sort(list, new Comparator<Times>() {
            @Override
            public int compare(Times o1, Times o2) {
                return (o2.getRanking().getCampeonato()).compareTo(o1.getRanking().getCampeonato());
            }
        });
        sectionAdapter.notifyDataSetChanged();

    }

    public static void sortUltimaASC() {

        Collections.sort(list, new Comparator<Times>() {
            @Override
            public int compare(Times o1, Times o2) {
                return (o2.getPontos().getRodada()).compareTo(o1.getPontos().getRodada());
            }
        });
        sectionAdapter.notifyDataSetChanged();

    }

    public static void sortUltimaDESC() {

        Collections.sort(list, new Comparator<Times>() {
            @Override
            public int compare(Times o1, Times o2) {
                return (o1.getPontos().getRodada()).compareTo(o2.getPontos().getRodada());
            }
        });
        sectionAdapter.notifyDataSetChanged();

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
