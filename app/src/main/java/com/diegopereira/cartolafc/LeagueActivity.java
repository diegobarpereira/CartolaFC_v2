package com.diegopereira.cartolafc;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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
import com.diegopereira.cartolafc.league.Players;
import com.diegopereira.cartolafc.league.RequestInterface;
import com.diegopereira.cartolafc.league.ServiceGenerator;
import com.diegopereira.cartolafc.league.TimePontos;
import com.diegopereira.cartolafc.league.Times;
import com.diegopereira.cartolafc.parciais.Parciais;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;
import static com.diegopereira.cartolafc.ligaauth.MySection.SHARED_PREF_SLUG;
import static com.diegopereira.cartolafc.ligaauth.MySection.SLUG_SHARED_PREF;
import static com.diegopereira.cartolafc.ligaauth.MySection.NAME_SHARED_PREF;


import static com.diegopereira.cartolafc.WebViewActivity.SHARED_PREF_NAME;
import static com.diegopereira.cartolafc.WebViewActivity.SHARED_TOKEN;


public class LeagueActivity extends AppCompatActivity {
    SharedPreferences preferences, token_preferences;
    String slug;
    RecyclerView recyclerView;
    public static List<Times> list = new ArrayList<>();

    Map<String, Double> mapparciais = new HashMap<>(); //
    public static ArrayList<TimePontos> teste = new ArrayList<>();

    public static SectionedRecyclerViewAdapter sectionAdapter;

    private DatabaseHelper database;


    TextView total, ultima;
    String atleta_ids;

    Double pontos;

    String nome;
    String token = "";
    String stat;

    private ProgressBar loadProgress;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
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
                    teste.clear();
                    loadParciais();
                    database.delete();

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
        Toast.makeText(getApplicationContext(), slug + " Activity", Toast.LENGTH_SHORT).show();

        loadProgress = (ProgressBar) findViewById(R.id.leagueprogressBar);
        recyclerView = findViewById(R.id.rv_league);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));




        if (stat.equals("1") || stat.equals("6")) {

            loadLeague();

        } if (stat.equals("2")) {

            com.diegopereira.cartolafc.league.MyParcialSection section2 = new MyParcialSection(getApplicationContext(), nome, teste, mapparciais);
            sectionAdapter.addSection(section2);

            recyclerView.setAdapter(sectionAdapter);
            database = new DatabaseHelper(getApplicationContext());
            getApplicationContext().deleteDatabase(database.getDatabaseName());

            for (int j = 0; j < 28; j++) {
                database.insert("nome", 0.0, 0.0, "", "", 0);
            }


            loadParciais();
        }





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


                com.diegopereira.cartolafc.league.MySection section1 = new MySection(getApplicationContext() ,  nome, list);
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

        //teste.clear();


        APIInterface league = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<League> call = league.getTime(token, slug);
        call.enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                loadProgress.setVisibility(View.GONE);

                //System.out.println(response.body().getTimes());
                list = response.body().getTimes();





                RequestInterface requestInterface = ServiceGenerator.getRetrofit().create(RequestInterface.class);
                for (int i = 0; i < list.size(); i++) {



                    Call<Players> parciaiscall = requestInterface.getTime(list.get(i).getTime_id().toString());
                    int finalI = i;
                    parciaiscall.enqueue(new Callback<Players>() {
                        @Override
                        public void onResponse(Call<Players> call, Response<Players> response) {
                            System.out.println("Get Atletas: " + response.body().getAtletas().toString());

                            com.diegopereira.cartolafc.league.TimePontos times = new TimePontos();
                            times.setNome(response.body().getTime().getNome());
                            times.setPontos(response.body().getPontosCampeonato());
                            times.setAtletas(response.body().getAtletas());
                            times.setCapitaoId(response.body().getCapitaoId());
                            times.setUrlEscudoPng(response.body().getTime().getUrl_escudo_png());
                            times.setTimeId(list.get(finalI).getTime_id());

                            teste.add(times);




                            final Gson gson = new GsonBuilder()
                                    .setLenient()
                                    .serializeNulls()
                                    .create();
                            Retrofit retrofit = new Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .baseUrl(CONSTANTS.BASE_URL)
                                    .build();
                            com.diegopereira.cartolafc.parciais.APIInterface requestInterface = retrofit.create(com.diegopereira.cartolafc.parciais.APIInterface.class);
                            Call<Parciais> call2 = requestInterface.getAtletas();

                            call2.enqueue(new Callback<Parciais>() {

                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onResponse(Call<Parciais> call, Response<Parciais> response) {
                                    loadProgress.setVisibility(View.GONE);

                                    if (response.code() == 200) {

                                        try {
                                            // edited here ,add toJson
                                            String jsonResponse = new Gson().toJson(response.body());
                                            JSONObject jsonObject = null;
                                            if (jsonResponse != null) {
                                                try {
                                                    jsonObject = new JSONObject(jsonResponse);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                                String rodada = jsonObject.optString("rodada");
                                                JSONObject key2 = jsonObject.optJSONObject("atletas");

                                                Iterator<String> sIterator = key2.keys();

                                                //mapparciais = new HashMap<>();

                                                while (sIterator.hasNext()) {
                                                    atleta_ids = sIterator.next();
                                                    JSONObject atletas = key2.optJSONObject(atleta_ids);

                                                    String apelido = atletas.getString("apelido");

                                                    pontos = atletas.getDouble("pontuacao");
                                                    //System.out.println("Atletas IDS: " + atleta_ids);

                                                    mapparciais.put(atleta_ids, pontos);
                                                    //System.out.println("MAPPARCIAISACT: " + mapparciais);

                                                }
                                            }



                                            sectionAdapter.notifyDataSetChanged();

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }

                                @Override
                                public void onFailure(Call<Parciais> call, Throwable t) {
                                    System.out.println(t.getMessage());
                                }
                            });

                        }







                        @Override
                        public void onFailure(Call<Players> call, Throwable t) {

                        }
                    });

                }




            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {

            }
        });

        }




//        public static void sortTotalDESC() {
//
//                    Collections.sort(list, new Comparator<Times>() {
//                        @Override
//                        public int compare(Times o1, Times o2) {
//                            return (o1.getRanking().getCampeonato()).compareTo(o2.getRanking().getCampeonato());
//                        }
//                    });
//            sectionAdapter.notifyDataSetChanged();
//
//        }
//
//
//    public static void sortTotalASC() {
//
//        Collections.sort(list, new Comparator<Times>() {
//            @Override
//            public int compare(Times o1, Times o2) {
//                return (o2.getRanking().getCampeonato()).compareTo(o1.getRanking().getCampeonato());
//            }
//        });
//        sectionAdapter.notifyDataSetChanged();
//
//    }
//
//    public static void sortUltimaASC() {
//
//        Collections.sort(list, new Comparator<Times>() {
//            @Override
//            public int compare(Times o1, Times o2) {
//                return (o2.getPontos().getRodada()).compareTo(o1.getPontos().getRodada());
//            }
//        });
//        sectionAdapter.notifyDataSetChanged();
//
//    }
//
//    public static void sortUltimaDESC() {
//
//        Collections.sort(list, new Comparator<Times>() {
//            @Override
//            public int compare(Times o1, Times o2) {
//                return (o1.getPontos().getRodada()).compareTo(o2.getPontos().getRodada());
//            }
//        });
//        sectionAdapter.notifyDataSetChanged();
//
//    }







    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
