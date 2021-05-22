package com.diegopereira.cartolafc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.parciais.Parciais;
import com.diegopereira.cartolafc.teste.APIInterface;
import com.diegopereira.cartolafc.teste.Players;
import com.diegopereira.cartolafc.teste.RecyclerRodadaAdapter;
import com.diegopereira.cartolafc.teste.RecyclerViewAdapter;
import com.diegopereira.cartolafc.teste.ServiceGenerator;
import com.diegopereira.cartolafc.teste.TimePontos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;

public class Teste2Activity extends AppCompatActivity {

    private ProgressBar loadProgress;

    Map<String, Double> mapparciais = new HashMap<>(); //
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<TimePontos> teste = new ArrayList<>();

    private DatabaseHelper database;

    RecyclerView rv_teste, rv_liga;
    RecyclerViewAdapter adapter;
    RecyclerRodadaAdapter rodadapter;

    LinearLayoutManager linearLayoutManager, ll_liga;

    TextView total, ultima;
    String atleta_ids;
    String stat;

    Double pontos;

    @SuppressLint("CutPasteId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        list.add(1241021); //Fandangos Santista
        list.add(19190102); // ThiagoRolo FC
        list.add(1893918); // Christimao
        list.add(1889674); // 0 VINTE1 FC
        list.add(1011904); // VS Ponte Preta
        list.add(2611962); // SAANTASTIC0FC
        list.add(13957925); // oSantista
        list.add(7630223); // DraVascular
        list.add(1235701); // Eae Malandro FC
        list.add(1245808); // Diego Pereira FC
        list.add(25582672); // JevyGoal
        list.add(977136); // RIVA 77
        list.add(219929); // Alb2106 FC
        list.add(3487572); // Labareda's FC
        list.add(71375); // Denoris F.C.
        list.add(579336); // AC MeTeLiGoLi
        list.add(937747); // RLR Santos FC
        list.add(14439636); // Obina Mais Dez
        list.add(19883717); // Lanterna Football Club
        list.add(54685); // Sóhh Tapa F.C.
        list.add(19186489); // FluminenseCangaiba
        list.add(25588958); // JUNA FUTEBOL CLUBE
        list.add(25779593); // RaFla86
        list.add(19871896); // Tchuko F.C.
        list.add(315637); // Real Beach Soccer
        list.add(18507); // STRONDA SANTOS
        list.add(18796615); // Golden Lions SC
        list.add(279314); // AvantiHulkFc

        ultima = (TextView) findViewById(R.id.ultima);
        total = (TextView) findViewById(R.id.total);
        loadProgress = (ProgressBar) findViewById(R.id.testeprogressBar);

        rv_teste = findViewById(R.id.rv_teste);
        rv_liga = findViewById(R.id.rv_teste);
        rv_teste.setHasFixedSize(true);
        rv_liga.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        ll_liga = new LinearLayoutManager(getApplicationContext());
        ll_liga.setOrientation(LinearLayoutManager.VERTICAL);

        rv_teste.setLayoutManager(linearLayoutManager);
        rv_liga.setLayoutManager(ll_liga);
        rv_teste.addItemDecoration(new DividerItemDecoration(rv_teste.getContext(), DividerItemDecoration.VERTICAL));
        rv_liga.addItemDecoration(new DividerItemDecoration(rv_liga.getContext(), DividerItemDecoration.VERTICAL));

        //
        //adapter = new RecyclerViewAdapter(Teste2Activity.this, teste);
        //rv_liga.setAdapter(adapter);
        rodadapter = new RecyclerRodadaAdapter(Teste2Activity.this, teste, mapparciais);
        rv_teste.setAdapter(rodadapter);
        //

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh8);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SharedPreferences sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
                stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");

                if (stat.equals("1")) {
                    //teste.clear();
                    //adapter.notifyDataSetChanged();
                    loadLiga(); // your code
                }
                if (stat.equals("2")) {
                    //teste.clear();
                    //rodadapter.notifyDataSetChanged();
                    loadParciais();
                    database.delete();
                }
                pullToRefresh.setRefreshing(false);
            }
        });

        SharedPreferences sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");

        if (stat.equals("1")) {
            loadLiga();
        }

        if (stat.equals("2")) {
            ultima.setText("Parciais");
            loadParciais();
        }

    }

    private void loadLiga() {

        teste.clear();

        APIInterface status = ServiceGenerator.getRetrofit().create(APIInterface.class);

        for (int i = 0; i < list.size(); i++) {
            Call<Players> call = status.getTime(list.get(i).toString());

            int finalI = i;
            call.enqueue(new Callback<Players>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<Players> call, Response<Players> response) {
                    loadProgress.setVisibility(View.GONE);

                    System.out.println(response.code());


                    TimePontos times = new TimePontos();
                    times.setNome(response.body().getTime().getNome());
                    times.setPontos(response.body().getPontosCampeonato());
                    times.setUltima(response.body().getPontos());
                    times.setUrlEscudoPng(response.body().getTime().getUrlEscudoPng());
                    times.setPatrimonio(response.body().getPatrimonio());
                    times.setTimeId(list.get(finalI));

                    teste.add(times);

                    Collections.sort(teste, new Comparator<TimePontos>() {
                        @Override
                        public int compare(TimePontos o1, TimePontos o2) {
                            return o2.getPontos().compareTo(o1.getPontos());
                        }
                    });

                    adapter = new RecyclerViewAdapter(getApplicationContext(), teste);
                    rv_liga.setAdapter(adapter);

                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Players> call, Throwable t) {
                    Log.e("CEPService   ", "Erro ao buscar o time:" + t.getMessage());
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loadParciais() {

        teste.clear();

        database = new DatabaseHelper(getApplicationContext());
        for (int j = 0; j < 28; j++) {
            database.insert("nome", 0.0, 0.0, "", "", 0);
        }

        APIInterface status = ServiceGenerator.getRetrofit().create(APIInterface.class);

        for (int i = 0; i < list.size(); i++) {
            Call<Players> call = status.getTime(list.get(i).toString());
            int finalI = i;

            call.enqueue(new Callback<Players>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<Players> call, Response<Players> response) {

                    TimePontos times = new TimePontos();
                    times.setNome(response.body().getTime().getNome());
                    times.setPontos(response.body().getPontosCampeonato());
                    times.setAtletas(response.body().getAtletas());
                    times.setCapitaoId(response.body().getCapitaoId());
                    times.setUrlEscudoPng(response.body().getTime().getUrlEscudoPng());
                    times.setTimeId(list.get(finalI));

                    teste.add(times);

                    final Gson gson = new GsonBuilder()
                            .setLenient()
                            .serializeNulls()
                            .create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            //.baseUrl("https://jsonkeeper.com")
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

                                    //rodadapter = new RecyclerRodadaAdapter(Teste2Activity.this, teste, mapparciais);
                                    //rv_teste.setAdapter(rodadapter);

                                    rodadapter.notifyDataSetChanged();

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
                    Log.e("CEPService   ", "Erro ao buscar o time:" + t.getMessage());
                }
            });

        }

        }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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