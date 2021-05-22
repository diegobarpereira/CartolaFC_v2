package com.diegopereira.cartolafc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.diegopereira.cartolafc.login.LigaGenerator;
import com.diegopereira.cartolafc.login.RequestInterface;
import com.diegopereira.cartolafc.login.Response;
import com.diegopereira.cartolafc.login.ServiceGenerator;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button auth;

    public static final String SHARED_PREF_NAME = "SHARED";
    public static final String SHARED_TOKEN = "TOKEN";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        auth = (Button) findViewById(R.id.auth);



        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                System.out.println(username.getText().toString());
                System.out.println(password.getText().toString());

                String user = username.getText().toString();
                String pass = password.getText().toString();

                RequestInterface requestInterface = ServiceGenerator.getRetrofit().create(RequestInterface.class);

                String json = "{\"payload\": {\"email\" : \"" + user + "\",\"password\": \"" + pass + "\",\"serviceId\": 4728}}";
                //String json = "{\"payload\": {\"email\" :\"diegobarpereira@gmail.com\",\"password\":\"eky0618GolG5\",\"serviceId\": 4728}}";
                System.out.println(json);

                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(json));
                Call<ResponseBody> call = requestInterface.getLogin(body);

                call.enqueue(new Callback<ResponseBody>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse( Call<ResponseBody> call, retrofit2.Response<ResponseBody> response ) {
                        System.out.println(response.code());
                        System.out.println(response.headers());
                        System.out.println(response.body());

                        if (response.isSuccessful()) {
                            String token = "";
                            try {
                                String json = response.body().string();
                                System.out.println("JSON: " + json);
                                JSONObject glbId = new JSONObject(json);
                                token = glbId.getString("glbId");
                                System.out.println("TOKEN: " + token);

                                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();

                                editor.putString(SHARED_TOKEN, token);
                                editor.apply();



                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(getApplicationContext(), "Logado!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Login Incorreto!", Toast.LENGTH_SHORT).show();
                        }

                    }






                    @Override
                    public void onFailure( Call<ResponseBody> call, Throwable t ) {
                        System.out.println(t.getStackTrace());
                    }
                });

            }
        });




    }
}