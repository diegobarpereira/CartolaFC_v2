package com.diegopereira.cartolafc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.destaques.APIInterface;
import com.diegopereira.cartolafc.destaques.Destaques;
import com.diegopereira.cartolafc.destaques.DestaquesRecyclerAdapter;
import com.diegopereira.cartolafc.teste.GridSpacingItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaisEscalados extends AppCompatActivity {
    private ProgressBar loadProgress;
    private RecyclerView recyclerView;
    private DestaquesRecyclerAdapter recyclerAdapter;
    private List<Destaques> destaques;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destaques);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDestaques(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });
        loadProgress = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        linearLayoutManager=new LinearLayoutManager(MaisEscalados.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(linearLayoutManager);

        int spanCount = 2; // 2 columns
        int spacing = 25; // 50px
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        loadDestaques();

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

    public void loadDestaques() {
        APIInterface service= ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Destaques>> call=service.getDestaques();
        call.enqueue(new Callback<List<Destaques>>() {
            @Override
            public void onResponse(Call<List<Destaques>> call, Response<List<Destaques>> response) {
                loadProgress.setVisibility(View.GONE);
                destaques=response.body();
                recyclerAdapter=new DestaquesRecyclerAdapter(MaisEscalados.this,destaques);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<Destaques>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
