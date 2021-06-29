package com.diegopereira.cartolafc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.liga.APIInterface;
import com.diegopereira.cartolafc.liga.Atleta;
import com.diegopereira.cartolafc.liga.Clubes;
import com.diegopereira.cartolafc.liga.LigaRecyclerAdapter;
import com.diegopereira.cartolafc.liga.LigaRodadaAdapter;
import com.diegopereira.cartolafc.liga.MyParcialSection;
import com.diegopereira.cartolafc.liga.Players;
import com.diegopereira.cartolafc.liga.Posicoes;
import com.diegopereira.cartolafc.liga.ServiceGenerator;
import com.diegopereira.cartolafc.liga.Teste;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;
import static com.diegopereira.cartolafc.league.MyParcialSection.QTY_SHARED_PREF;
import static com.diegopereira.cartolafc.league.MyParcialSection.TOTAL_SHARED_PREF;


public class LigaActivity extends AppCompatActivity implements ServiceConnection {
    private ChamadaMetodo s;

    RecyclerView recyclerView;
    List<Atleta> list = new ArrayList<>();
    List<Atleta> reservas = new ArrayList<>();
    Map<Integer, Clubes> clubes = new HashMap();
    Map<Integer, Posicoes> posicoes = new HashMap();
    Integer capitao, atleta_id;
    String timename, image, atleta_ids;
    Double timepts, timetot, pontos;
    List<Map.Entry<String, Atletas>> wordList = new ArrayList<Map.Entry<String, Atletas>>();

    String vault = "";
    String qty = "";
    String cap = "";
    String stat = "";
    String time_id = "";

    AppCompatTextView tv_timename, tv_timepts, tv_timetot, tv_qty;
    AppCompatImageView img_time;

    LigaRecyclerAdapter adapter;
    //LigaRodadaAdapter rodadapter;

    public static SectionedRecyclerViewAdapter sectionAdapter;


    SharedPreferences preferences;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liga);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        tv_timename = (AppCompatTextView) findViewById(R.id.tv_timename);
        img_time = (AppCompatImageView) findViewById(R.id.img_time);
        tv_timepts = (AppCompatTextView) findViewById(R.id.tv_timepts);
        tv_timetot = (AppCompatTextView) findViewById(R.id.tv_timetot);
        tv_qty = (AppCompatTextView) findViewById(R.id.tv_qty);

        sectionAdapter = new SectionedRecyclerViewAdapter();


        recyclerView = findViewById(R.id.rv_liga);
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

        preferences = getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);

        time_id = preferences.getString("ID_SHARED_PREF", "");

        qty = preferences.getString(QTY_SHARED_PREF, String.valueOf("0/0"));

        sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");
        //stat = "2";

        if (stat.equals("1")) {
            loadLiga();

        }
        if (stat.equals("2")) {
            Handler handler = new Handler();
            long delay = 2000; // tempo de delay em millisegundos
            handler.postDelayed(new Runnable() {
                public void run() {
                    loadParciais();
                }
            }, delay);
        }

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh6);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (stat.equals("1")) {
                    adapter.notifyDataSetChanged();
                    loadLiga(); // your code

                }
                if (stat.equals("2")) {
                    sectionAdapter.removeAllSections();
                    sectionAdapter.notifyDataSetChanged();
                    loadParciais();
                }

                pullToRefresh.setRefreshing(false);
            }
        });

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

    public void loadLiga() {

        APIInterface status = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<Players> call = status.getTime(String.valueOf(time_id));

        call.enqueue(new Callback<Players>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Players> call, Response<Players> response) {
                if (response.isSuccessful()) {
                    timename = response.body().getTime().getNome();
                    tv_timename.setText(timename);

                    image = response.body().getTime().getUrlEscudoPng();

                    Picasso.with(getApplicationContext())
                            .load(image)
                            .into(img_time);

                    String rodadaatual = MainActivity.rodada;
                    if (stat.equals("1") && (String.valueOf(rodadaatual).equals("1"))) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Este time ainda não foi \n escalado na temporada.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                        LinearLayout toastLL = (LinearLayout) toast.getView();
                        toastLL.setBackgroundColor(Color.WHITE);
                        TextView toastTV = (TextView) toastLL.getChildAt(0);
                        toastTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        toastTV.setTextColor(Color.BLACK);
                        toastTV.setTextSize(22);
                        toast.show();
                    } else {

                        list = response.body().getAtletas();
                        reservas = response.body().getReservas();

                        Collections.sort(list, new Comparator<Atleta>() {
                            @Override
                            public int compare(Atleta o1, Atleta o2) {
                                return o1.getPosicaoId().compareTo(o2.getPosicaoId());
                            }
                        });
                        Collections.sort(reservas, new Comparator<Atleta>() {
                            @Override
                            public int compare(Atleta o1, Atleta o2) {
                                return o1.getPosicaoId().compareTo(o2.getPosicaoId());
                            }
                        });

                        capitao = response.body().getCapitaoId();
                        atleta_id = response.body().getTime().getTimeId();

                        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");

                        clubes = response.body().getClubes();
                        posicoes = response.body().getPosicoes();


                            timepts = response.body().getPontos();
                            tv_timepts.setText(String.format(Locale.US, "%.2f", timepts));
                            timetot = response.body().getPontosCampeonato();
                            tv_timetot.setText(String.format(Locale.US, "%.2f", timetot));
                            tv_qty.setVisibility(View.INVISIBLE);
                            adapter = new LigaRecyclerAdapter(getApplicationContext(), list, capitao, posicoes, clubes);
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);


                    }
                }

            }

            @Override
            public void onFailure(Call<Players> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    public void loadParciais() {

        APIInterface status = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<Players> call = status.getTime(String.valueOf(time_id));

        call.enqueue(new Callback<Players>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Players> call, Response<Players> response) {
                if (response.isSuccessful()) {

                    vault = preferences.getString(TOTAL_SHARED_PREF, String.valueOf(0.0));
                    qty = preferences.getString(QTY_SHARED_PREF, String.valueOf("0/0"));

                    Double vvault = Double.valueOf(vault);
                    Double timepts_ = (vvault);
                    tv_timepts.setText(String.format(Locale.US, "%.2f", timepts_));

                    tv_qty.setText(qty);

                    timetot = response.body().getPontosCampeonato();
                    tv_timetot.setText(String.format(Locale.US, "%.2f", (timetot + timepts_)));

                    timename = response.body().getTime().getNome();
                    tv_timename.setText(timename);

                    image = response.body().getTime().getUrlEscudoPng();

                    Picasso.with(getApplicationContext())
                            .load(image)
                            .into(img_time);

                    list = response.body().getAtletas();
                    reservas = response.body().getReservas();

                    Collections.sort(list, new Comparator<Atleta>() {
                        @Override
                        public int compare(Atleta o1, Atleta o2) {
                            return o1.getPosicaoId().compareTo(o2.getPosicaoId());
                        }
                    });
                    Collections.sort(reservas, new Comparator<Atleta>() {
                        @Override
                        public int compare(Atleta o1, Atleta o2) {
                            return o1.getPosicaoId().compareTo(o2.getPosicaoId());
                        }
                    });

                    capitao = response.body().getCapitaoId();
                    atleta_id = response.body().getTime().getTimeId();
                    clubes = response.body().getClubes();
                    posicoes = response.body().getPosicoes();

                    Intent service = new Intent(getApplicationContext(), ChamadaMetodo.class);
                    getApplicationContext().startService(service);

                    if (s != null) {
                        Toast.makeText(getApplicationContext(), "Number of elements: " + s.getWordList().size(),
                                Toast.LENGTH_SHORT).show();
                        wordList.clear();
                        wordList.addAll(s.getWordList());

                    }

                    String tit_title = "Titulares";
                    String res_title = "Reservas";

                    MyParcialSection tit_section = new MyParcialSection(getApplicationContext(), tit_title, list, clubes, posicoes, wordList, capitao);
                    sectionAdapter.addSection(tit_section);

                    MyParcialSection res_section = new MyParcialSection(getApplicationContext(), res_title, reservas, clubes, posicoes, wordList, capitao);
                    sectionAdapter.addSection(res_section);
                    sectionAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<Players> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });

    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        ChamadaMetodo.MyBinder b = (ChamadaMetodo.MyBinder) binder;
        s = b.getService();
        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        s = null;
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

        if (id == R.id.classi) {
            Intent intent = new Intent(getApplicationContext(), ClassificacaoActivity.class);
            startActivity(intent);
            return true;
        }
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