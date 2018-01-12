package com.example.madamkinza.retrofitokhttp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Button btn;
    private EditText etJsonResponse;
    private OkHttpClient okHttpClient;
    private Request request;
    private String url = "http://192.168.10.2:8000/articles";
    //private String url = "https://contact.mocklab.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        etJsonResponse = (EditText)findViewById(R.id.etJsonResponse);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                okHttpClient = new OkHttpClient();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();

                /*builder.connectTimeout(30, TimeUnit.SECONDS);
                builder.readTimeout(30, TimeUnit.SECONDS);
                builder.writeTimeout(30, TimeUnit.SECONDS);
                okHttpClient = builder.build();*/

                request = new Request.Builder().url(url).build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i(TAG,e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i(TAG,response.body().string());

                    }
                });

                /*try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.i(TAG,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

    }
}