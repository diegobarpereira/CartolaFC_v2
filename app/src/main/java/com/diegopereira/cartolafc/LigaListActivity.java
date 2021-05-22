package com.diegopereira.cartolafc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LigaListActivity extends AppCompatActivity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligalist);
        final ListView list = findViewById(R.id.list);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Fandangos Santista");
        arrayList.add("ThiagoRolo FC");
        arrayList.add("Christimao");
        arrayList.add("0VINTE1 FC");
        arrayList.add("SAANTASTIC0FC");
        arrayList.add("oSantista");
        arrayList.add("DraVascular");
        arrayList.add("Eae Malandro FC");
        arrayList.add("Diego Pereira FC");
        arrayList.add("JevyGoal");
        arrayList.add("RIVA 77");
        arrayList.add("Alb2106 FC");
        arrayList.add("Labareda's FC");
        arrayList.add("Denoris F.C.");
        arrayList.add("AC MeTeLiGoLi");
        arrayList.add(" RLR Santos FC");
        arrayList.add("Obina Mais Dez");
        arrayList.add("Lanterna Football Club");
        arrayList.add("Sóhh Tapa F.C.");
        arrayList.add("FluminenseCangaiba");
        arrayList.add("JUNA FUTEBOL CLUBE");
        arrayList.add("RaFla86");
        arrayList.add("Tchuko F.C.");
        arrayList.add("Real Beach Soccer");
        arrayList.add("STRONDA SANTOS");
        arrayList.add("Golden Lions SC");
        arrayList.add("AvantiHulkFc");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) list.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),clickedItem,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
