package com.diegopereira.cartolafc;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.groups.APIInterface;
import com.diegopereira.cartolafc.groups.ApiClient;
import com.diegopereira.cartolafc.groups.DatabaseHelper;
import com.diegopereira.cartolafc.groups.GroupRecyclerAdapter;
import com.diegopereira.cartolafc.groups.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupActivity extends AppCompatActivity {

    RecyclerView rv_group;
    List<Input> list = new ArrayList<>();
    List<String> str;
    List<Input> input = new ArrayList<>();
    GroupRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    AutoCompleteTextView autoCompleteTextView;
    String text;
    ArrayAdapter<String> actvadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);

        rv_group = findViewById(R.id.rv_group);
        rv_group.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_group.setLayoutManager(linearLayoutManager);

        //adapter = new GroupRecyclerAdapter(getApplicationContext(), input);
        //rv_group.setAdapter(adapter);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocompletetv); // inititate a search view

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text = String.valueOf(charSequence);
                System.out.println("Query: " + text);

                getGroup();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), list.get(i).getTimeId().toString(), Toast.LENGTH_SHORT).show();

                Input tinput = new Input();
                tinput.setNome(list.get(i).getNome());
                tinput.setNomeCartola(list.get(i).getNomeCartola());
                tinput.setUrlEscudoPng(list.get(i).getUrlEscudoPng());
                tinput.setTimeId(list.get(i).getTimeId());

                input.add(tinput);

                //adapter.notifyDataSetChanged();


                autoCompleteTextView.setText("");

                    System.out.println("TInput: " + input);


                adapter = new GroupRecyclerAdapter(getApplicationContext(), input);
                rv_group.setAdapter(adapter);

            }
        });

    }


    public void getGroup() {


                System.out.println("getgroup: " + text);
                APIInterface service = ApiClient.getRetrofit().create(APIInterface.class);
                Call<List<Input>> call = service.getAtletas(text);

                call.enqueue(new Callback<List<Input>>() {
                    @Override
                    public void onResponse(Call<List<Input>> call, Response<List<Input>> response) {
                        System.out.println("Response: " + response.body().toString());
                        list = response.body();

                        str = new ArrayList<>();

                        for (Input s : response.body()) {
                            str.add(s.getNome());

                        }
                        System.out.println("Get Nome: " + str);

                        actvadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, str);
                        autoCompleteTextView.setAdapter(actvadapter);
                        autoCompleteTextView.setThreshold(1);

                        actvadapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<Input>> call, Throwable t) {
                        System.out.println("Failure: " + t.getMessage());
                    }
                });

    }
}
