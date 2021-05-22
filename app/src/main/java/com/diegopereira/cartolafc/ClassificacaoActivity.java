package com.diegopereira.cartolafc;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.classificacao.ClassificacaoRecyclerAdapter;
import com.diegopereira.cartolafc.classificacao.Login;
import com.diegopereira.cartolafc.classificacao.Tabela;
import com.diegopereira.cartolafc.classificacao.User;
import com.diegopereira.cartolafc.classificacao.UserClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassificacaoActivity extends AppCompatActivity {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.api-futebol.com.br/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    RecyclerView rv_classificacao;
    private ProgressBar loadProgress;

    List<Tabela> list = new ArrayList<>();

    ClassificacaoRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager, linearLayoutManager_;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificacao);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        loadProgress = (ProgressBar) findViewById(R.id.classprogressBar);

        rv_classificacao = findViewById(R.id.rv_classificacao);
        rv_classificacao.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_classificacao.setLayoutManager(linearLayoutManager);
        rv_classificacao.addItemDecoration(new DividerItemDecoration(rv_classificacao.getContext(), DividerItemDecoration.VERTICAL));
        rv_classificacao.addItemDecoration(new DividerItemDecoration(rv_classificacao.getContext(), DividerItemDecoration.HORIZONTAL));

        adapter = new ClassificacaoRecyclerAdapter(getApplicationContext(), list);

        rv_classificacao.setAdapter(adapter);

        getSecret();

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.classRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               getSecret();

                pullToRefresh.setRefreshing(false);
            }
        });

    }

    private static String token = "live_4db8ca54e26638c9598df99ac63937";

    private void login() {
        Login login = new Login("diegobarpereira@gmail.com", "23011954");
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse( Call<User> call, Response<User> response ) {
                System.out.println(response.code());
                System.out.println(response.body());

                if(response.isSuccessful()) {
                    Toast.makeText(ClassificacaoActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                } else {
                    Toast.makeText(ClassificacaoActivity.this, "Response Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure( Call<User> call, Throwable t ) {
                Toast.makeText(ClassificacaoActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSecret() {

        User user = new User();
        user.setToken(token);
        System.out.println(user.getToken());

        Call<ResponseBody> call = userClient.getSecret("Bearer "+token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse( Call<ResponseBody> call, Response<ResponseBody> response ) {
                loadProgress.setVisibility(View.GONE);

                if(response.isSuccessful()) {
                    String jsonOutput = null;
                    try {
                        jsonOutput = response.body().string();
                        System.out.println(jsonOutput);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(jsonOutput);

                        JSONArray key = jsonObject.getJSONArray("tabela");

                        Integer posicao = 0;
                        Integer pontos = 0;
                        Integer jogos = 0;
                        Integer vitorias = 0;
                        Integer empates = 0;
                        Integer derrotas = 0;
                        Integer gols_pro = 0;
                        Integer gols_contra = 0;
                        Integer saldo_gols = 0;
                        Double aproveitamento = 0.0;
                        Integer variacao_posicao = 0;
                        String nome_popular = "";

                        //List<Tabela> list = new ArrayList<>();

                        for (int i = 0; i < key.length(); i++) {
                            JSONObject key2 = key.getJSONObject(i);

                            posicao = key2.getInt("posicao");
                            pontos = key2.getInt("pontos");
                            jogos = key2.getInt("jogos");
                            vitorias = key2.getInt("vitorias");
                            empates = key2.getInt("empates");
                            derrotas = key2.getInt("derrotas");
                            gols_pro = key2.getInt("gols_pro");
                            gols_contra = key2.getInt("gols_contra");
                            saldo_gols = key2.getInt("saldo_gols");
                            aproveitamento = key2.getDouble("aproveitamento");
                            variacao_posicao = key2.getInt("variacao_posicao");

                            JSONObject jsonObject1 = key2.getJSONObject("time");
                            nome_popular = jsonObject1.getString("nome_popular");

                            Tabela tabela = new Tabela();
                            tabela.setPosicao(posicao);
                            tabela.setPontos(pontos);
                            tabela.setJogos(jogos);
                            tabela.setVitorias(vitorias);
                            tabela.setEmpates(empates);
                            tabela.setDerrotas(derrotas);
                            tabela.setGolsPro(gols_pro);
                            tabela.setGolsContra(gols_contra);
                            tabela.setSaldoGols(saldo_gols);
                            tabela.setAproveitamento(aproveitamento);
                            tabela.setVariacaoPosicao(variacao_posicao);
                            tabela.setTime(nome_popular);

                            list.add(tabela);

                        }

                        System.out.println(list);

                        //adapter = new ClassificacaoRecyclerAdapter(getApplicationContext(), list);
                        //rv_classificacao.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(ClassificacaoActivity.this, "token Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure( Call<ResponseBody> call, Throwable t ) {
                Toast.makeText(ClassificacaoActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
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
            Intent intent = new Intent(getApplicationContext(), LigaActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
