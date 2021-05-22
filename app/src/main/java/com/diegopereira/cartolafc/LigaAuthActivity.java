package com.diegopereira.cartolafc;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
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

import com.diegopereira.cartolafc.ligaauth.AuthLiga;
import com.diegopereira.cartolafc.ligaauth.Ligas;
import com.diegopereira.cartolafc.ligaauth.MySection;
import com.diegopereira.cartolafc.login.LigaGenerator;
import com.diegopereira.cartolafc.login.RequestInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diegopereira.cartolafc.WebViewActivity.SHARED_PREF_NAME;
import static com.diegopereira.cartolafc.WebViewActivity.SHARED_TOKEN;

public class LigaAuthActivity extends AppCompatActivity {
    SharedPreferences preferences;
    RecyclerView recyclerView;
    //RecyclerViewAdapter adapter;
    //Adapter adapter;

    List<Ligas> ligas = new ArrayList<>();
    List<Ligas> list1 = new ArrayList<>();
    List<Ligas> list2 = new ArrayList<>();
    //MySection section1 = new MySection(this, "Ligas Privadas", list1);
    //MySection section2 = new MySection(this, "Ligas Públicas", list2);


    boolean sync;
    String token = "";
    private ProgressBar loadProgress;

    SectionedRecyclerViewAdapter sectionAdapter;

    SwipeRefreshLayout pullToRefresh;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligaauth);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        pullToRefresh = findViewById(R.id.pullToRefresh9);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(sync) {
                    list1.clear();
                    list2.clear();
                    loadLigas(); // your code

                    pullToRefresh.setRefreshing(true);
                } else {
                    pullToRefresh.setRefreshing(false);
                }
            }

        });

        loadProgress = (ProgressBar) findViewById(R.id.ligaprogressBar);


        sectionAdapter = new SectionedRecyclerViewAdapter();
        //sectionAdapter.addSection(new MySection());

        recyclerView = findViewById(R.id.rv_ligaauth);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        token = preferences.getString(SHARED_TOKEN, "");

        System.out.println("token: " + token);

        if (token.isEmpty()) {
            loadProgress.setVisibility(View.GONE);
            Toast toast = Toast.makeText(getApplicationContext(), "Não Logado!!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
            LinearLayout toastLL = (LinearLayout) toast.getView();
            toastLL.setBackgroundColor(Color.WHITE);
            TextView toastTV = (TextView) toastLL.getChildAt(0);
            toastTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            toastTV.setTextColor(Color.BLACK);
            toastTV.setTextSize(22);
            toast.show();
        } else {
            loadLigas();
        }

    }

    private void loadLigas() {
        RequestInterface requestInterface = LigaGenerator.getRetrofit().create(RequestInterface.class);
        Call<ResponseBody> call2 = requestInterface.getLigas(token);
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse( Call<ResponseBody> call, retrofit2.Response<ResponseBody> response ) {
                try {
                    System.out.println(response.body().string());

                    com.diegopereira.cartolafc.ligaauth.RequestInterface requestInterface1 = ServiceGenerator.getRetrofit().create(com.diegopereira.cartolafc.ligaauth.RequestInterface.class);
                    Call<AuthLiga> call3 = requestInterface1.getLigas(token);
                    call3.enqueue(new Callback<AuthLiga>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse( Call<AuthLiga> call, Response<AuthLiga> response ) {
                            loadProgress.setVisibility(View.GONE);


                            System.out.println("Get Ligas: " + response.body().getLigas().toString());
                            ligas = response.body().getLigas();

                            String gson = new Gson().toJson(response.body().getLigas());
                            System.out.println("gson: " + gson);

                            if (!containsName(ligas, "null")) {


                                for (Ligas entry : ligas) {
                                    if (entry.getTime_dono_id() != null) {
                                        if (ligas.isEmpty()) {

                                        } else {
                                            list1.add(entry);

                                        }
                                        System.out.println("Teste: " + list1);
                                    } else {
                                        list2.add(entry);

                                        System.out.println("Teste 2: " + list2);
                                    }
                                }




                            }

                                if (list1.size() > 0) {
                                    MySection section1 = new MySection(getApplicationContext(), "Ligas Privadas", list1);
                                    sectionAdapter.addSection(section1);
                                } else {

                                }
                                    MySection section2 = new MySection(getApplicationContext(), "Ligas Públicas", list2);
                                    sectionAdapter.addSection(section2);




                                Collections.sort(ligas, new Comparator<Ligas>() {
                                @Override
                                public int compare(Ligas o1, Ligas o2) {
                                    String x1 = null;
                                    String x2 = null;

                                    if (o1.getTime_dono_id() == null ) {
                                        x1 = "1";
                                    } else {
                                        x1 = o1.getTime_dono_id();
                                    }
                                    if (o2.getTime_dono_id() == null) {
                                        x2 = "1";
                                    } else {
                                        x2 = o2.getTime_dono_id();
                                    }



                                    System.out.println(x1 + " " + x2);
                                    return x2.compareTo(x1);
                                }
                            });



                            //adapter = new RecyclerViewAdapter(getApplicationContext(), ligas);




                            //adapter = new Adapter(getApplicationContext(), ligas);
                            //adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(sectionAdapter);


                        }

                        @Override
                        public void onFailure( Call<AuthLiga> call, Throwable t ) {

                        }
                    });



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure( Call<ResponseBody> call, Throwable t ) {

            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean containsName(final List<Ligas> list, final String time_dono_id){
        return list.stream().anyMatch(o -> o.getTime_dono_id() != null && o.getTime_dono_id().contains(time_dono_id));
    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
