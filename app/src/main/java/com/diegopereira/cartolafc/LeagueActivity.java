package com.diegopereira.cartolafc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
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
import com.diegopereira.cartolafc.league.Ranking;
import com.diegopereira.cartolafc.league.ServiceGenerator;
import com.diegopereira.cartolafc.league.Teste;
import com.diegopereira.cartolafc.league.Times;
import com.diegopereira.cartolafc.liga.Atleta;
import com.diegopereira.cartolafc.liga.Players;
import com.diegopereira.cartolafc.liga.Reservas;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.diegopereira.cartolafc.partidas.Partida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;
import static com.diegopereira.cartolafc.WebViewActivity.SHARED_PREF_NAME;
import static com.diegopereira.cartolafc.WebViewActivity.SHARED_TOKEN;
import static com.diegopereira.cartolafc.ligaauth.MySection.NAME_SHARED_PREF;
import static com.diegopereira.cartolafc.ligaauth.MySection.SHARED_PREF_SLUG;
import static com.diegopereira.cartolafc.ligaauth.MySection.SLUG_SHARED_PREF;


public class LeagueActivity extends AppCompatActivity implements ServiceConnection {
    private ChamadaMetodo s;

    SharedPreferences preferences, token_preferences;
    String slug;
    public static RecyclerView recyclerView;
    public static List<Times> list;
    public static List<Times> newList = new ArrayList<>();;
    Integer cap_id;
    List<Map.Entry<String, Atletas>> wordList = new ArrayList<Map.Entry<String, Atletas>>();
    List<Partida> partidas = new ArrayList<>();
    List<Atleta> atletas = new ArrayList<>();
    List<Atleta> reservas = new ArrayList<>();
    List<Integer> reservausado = new ArrayList<>();

    public static SectionedRecyclerViewAdapter sectionAdapter;
    public static MyParcialSection section2;

    Integer times_id;
    Integer posicao_antiga;

    String nome;
    String token = "";
    String stat;
    boolean jogou;

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
                    newList.clear();
                    sectionAdapter.removeAllSections();
                    sectionAdapter.notifyDataSetChanged();

                    Intent service = new Intent(getApplicationContext(), ChamadaMetodo.class);
                    getApplicationContext().startService(service);
                    loadParciais();

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

        loadProgress = (ProgressBar) findViewById(R.id.leagueprogressBar);
        recyclerView = findViewById(R.id.rv_league);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
        drawable.setSize(1,1);
        itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(sectionAdapter);




        if (stat.equals("1") || stat.equals("6")) {

            loadLeague();

        }
        if (stat.equals("2")) {

            Intent service = new Intent(getApplicationContext(), ChamadaMetodo.class);
            getApplicationContext().startService(service);

            Handler handler = new Handler();
            long delay = 3000; // tempo de delay em millisegundos
            handler.postDelayed(new Runnable() {
                public void run() {
                    loadParciais();
                }
            }, delay);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        newList.clear();
        sectionAdapter.removeAllSections();
        sectionAdapter.notifyDataSetChanged();
        Intent intent = new Intent(this, ChamadaMetodo.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
        loadParciais();
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

                com.diegopereira.cartolafc.league.MySection section1 = new MySection(getApplicationContext(), nome, list);
                sectionAdapter.addSection(section1);
                sectionAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void loadParciais() {

        if (s != null) {
            Toast.makeText(getApplicationContext(), "Number of elements: " + s.getWordList().size(),
                    Toast.LENGTH_SHORT).show();
            wordList.clear();
            wordList.addAll(s.getWordList());
            partidas.clear();
            partidas.addAll(s.getPartidasList());

        }

        APIInterface league = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<League> call = league.getTime(token, slug);
        call.enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                loadProgress.setVisibility(View.GONE);

                list = response.body().getTimes();

                section2 = new MyParcialSection(getApplicationContext(), nome, newList);
                sectionAdapter.addSection(section2);

                for (int position = 0; position < list.size(); position++) {
                    times_id = list.get(position).getTime_id();
                    posicao_antiga = list.get(position).getRanking().getCampeonato();

                    com.diegopereira.cartolafc.liga.APIInterface status =  com.diegopereira.cartolafc.liga.ServiceGenerator.getRetrofit().create( com.diegopereira.cartolafc.liga.APIInterface.class);

                    Call<Players> call_players = status.getTime(times_id.toString());

                    int finalI = position;
                    call_players.enqueue(new Callback<Players>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(Call<Players> call, Response<Players> response) {

                            atletas = response.body().getAtletas();
                            cap_id = response.body().getCapitaoId();

                            double total = 0;
                            double pts_parciais = 0;
                            double pts_cap = 0;
                            double res_parciais = 0;
                            reservausado.clear();
                            jogou = false;
                            boolean foundRes = false;
                            boolean jogo_acabou = false;

                            for (Atleta atleta : atletas) {
                                Optional<Double> result_cap = wordList
                                        .stream()
                                        .filter(map -> String.valueOf(cap_id).equals(map.getKey()))
                                        .map(map -> map.getValue().getPontuacao())
                                        .findFirst();
                                if (result_cap.isPresent()) {
                                    System.out.println("Capitao: " + result_cap.get());
                                    pts_cap = result_cap.get();
                                }
                                Optional<Double> result_tit = wordList
                                        .stream()
                                        .filter(map -> String.valueOf(atleta.getAtletaId()).equals(map.getKey()))
                                        .map(map -> map.getValue().getPontuacao())
                                        .findFirst();
                                if (result_tit.isPresent()) {
                                    System.out.println("Titulares: " + result_tit.get());
                                    pts_parciais += result_tit.get();
                                } else {
                                    foundRes = false;
                                    if (response.body().getReservas() == null) {

                                    } else {
                                        reservas = response.body().getReservas();
                                        for (Atleta reserva : reservas) {
                                            for (Partida partida : partidas) {
                                                if (atleta.getClubeId().equals(partida.getClubeCasaId()) || atleta.getClubeId().equals(partida.getClubeVisitanteId())) {

                                                    if (partida.getTransmissao().getLabel().equals("veja como foi")) {
                                                        jogo_acabou = true;
                                                    } else {
                                                        jogo_acabou = false;
                                                    }
                                                }
                                            }
                                            if (atleta.getPosicaoId().equals(reserva.getPosicaoId()) && !foundRes && jogo_acabou) {
                                                Optional<Double> result_res = wordList
                                                        .stream()
                                                        .filter(map2 -> String.valueOf(reserva.getAtletaId()).equals(map2.getKey()))
                                                        .map(map2 -> map2.getValue().getPontuacao())
                                                        .findFirst();
                                                if (result_res.isPresent()) {
                                                    System.out.println("Reservas: " + result_res.get());
                                                    res_parciais += result_res.get();
                                                    foundRes = true;
                                                }
                                            }
                                        }
                                    }

                                }
                            }


                            total = pts_parciais + res_parciais + pts_cap;

                            Times times = new Times();
                            times.setTime_id(list.get(finalI).getTime_id());
                            times.setUrl_escudo_png(list.get(finalI).getUrl_escudo_png());
                            times.setNew_variacao(list.get(finalI).getRanking().getCampeonato());
                            times.setNome(list.get(finalI).getNome());
                            times.setPontosParciais(total);
                            times.setTotal(list.get(finalI).getPontos().getCampeonato() + total);
                            newList.add(times);

                            Collections.sort(newList, new Comparator<Times>() {
                                @Override
                                public int compare(Times o1, Times o2) {
                                    return o2.getTotal().compareTo(o1.getTotal());
                                }
                            });

                            sectionAdapter.notifyDataSetChanged();


                        }

                        @Override
                        public void onFailure(Call<Players> call_players, Throwable t) {
                            Log.e("ERROR", t.getMessage());
                        }
                    });
                }




            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
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

        Collections.sort(newList, new Comparator<Times>() {
            @Override
            public int compare(Times o1, Times o2) {
                return o2.getTotal().compareTo(o1.getTotal());
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

        Collections.sort(newList, new Comparator<Times>() {
            @Override
            public int compare(Times o1, Times o2) {
                return o2.getPontosParciais().compareTo(o1.getPontosParciais());
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
