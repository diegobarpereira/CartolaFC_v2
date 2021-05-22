package com.diegopereira.cartolafc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.parciais.APIInterface;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.diegopereira.cartolafc.parciais.Parciais;
import com.diegopereira.cartolafc.parciais.ParciaisRecyclerAdapter;
import com.diegopereira.cartolafc.parciais.Posicoes;
import com.diegopereira.cartolafc.parciais.Clubes;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParciaisActivity extends AppCompatActivity {

    private ProgressBar loadProgress;
    RecyclerView rv_parciais;
    ParciaisRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    Set<Map.Entry<String,Atletas>> set;
    List<Map.Entry<String, Atletas>> list;
    Map<String, Atletas> atletas;
    Map<Integer, Posicoes> posicoes = new HashMap();
    Map<Integer, Clubes> clubes = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parciais);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh3);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadParciais(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

        loadProgress = (ProgressBar) findViewById(R.id.progressBar);
        rv_parciais = findViewById(R.id.rv_parciais);
        rv_parciais.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_parciais.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(rv_parciais.getContext(), DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{0xfff7f7f7, 0xfff7f7f7});
        drawable.setSize(1,1);
        itemDecoration.setDrawable(drawable);
        rv_parciais.addItemDecoration(itemDecoration);

        loadParciais();

    }

    public void loadParciais() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl("https://jsonkeeper.com")
                //.baseUrl(CONSTANTS.BASE_URL)
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<Parciais> call = service.getAtletas();

        call.enqueue(new Callback<Parciais>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse( Call<Parciais> call, Response<Parciais> response ) {

                loadProgress.setVisibility(View.GONE);

                if (response.code() == 200) {

                    atletas = response.body().getAtletas();
                    posicoes = response.body().getPosicoes();
                    clubes = response.body().getClubes();

                    set = atletas.entrySet();
                    list = new ArrayList<Map.Entry<String, Atletas>>(set);

                    Collections.sort(list, new Comparator<Map.Entry<String, Atletas>>() {
                        @Override
                        public int compare( Map.Entry<String, Atletas> t1, Map.Entry<String, Atletas> t2 ) {
                            return t2.getValue().getPontuacao().compareTo(t1.getValue().getPontuacao());
                        }
                    });

                    adapter = new ParciaisRecyclerAdapter(getApplicationContext(), list, posicoes, clubes);

                    rv_parciais.setAdapter(adapter);
                }

                if (response.code() == 204 || response.code() == 404) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Parciais não disponíveis!!! \n Aguarde a rodada \n Iniciar!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    LinearLayout toastLL = (LinearLayout) toast.getView();
                    toastLL.setBackgroundColor(Color.WHITE);
                    TextView toastTV = (TextView) toastLL.getChildAt(0);
                    toastTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    toastTV.setTextColor(Color.BLACK);
                    toastTV.setTextSize(22);
                    toast.show();
                }
            }

            @Override
            public void onFailure( Call<Parciais> call, Throwable t ) {
                System.out.println(t.getMessage());
            }
        });

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