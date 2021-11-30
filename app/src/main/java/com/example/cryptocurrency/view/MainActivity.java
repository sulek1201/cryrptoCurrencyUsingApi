package com.example.cryptocurrency.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cryptocurrency.Dtos.CurrencyRequestDto;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.service.CryptoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    List<CurrencyRequestDto> currencyRequestDtos;
    Logger logger = LoggerFactory.getLogger(MainActivity.class);
    ArrayList<String> currenntCurrencyPrices = new ArrayList<>();
    private static String BASE_URL = "https://rest.coinapi.io/v1/";
    List<TextView> textViews = new ArrayList<>();
    Retrofit retrofit;
    TextView aliceCurrentPriceView;
    TextView atlasCurrentPriceView;
    TextView blokCurrentPriceView;
    TextView boaCurrentPriceView;
    TextView chessCurrentPriceView;
    TextView cosCurrentPriceView;
    TextView hardCurrentPriceView;
    TextView iotxCurrentPriceView;
    TextView manaCurrentPriceView;
    TextView mblACurrentPriceView;
    TextView mboxCurrentPriceView;
    TextView nwcCurrentPriceView;
    TextView onxCurrentPriceView;
    TextView racaCurrentPriceView;
    TextView tlmCurrentPriceView;
    TextView zenCurrentPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aliceCurrentPriceView = findViewById(R.id.aliceCurrentPriceView);
        textViews.add(aliceCurrentPriceView);
        atlasCurrentPriceView = findViewById(R.id.atlasCurrentPriceView);
        textViews.add(atlasCurrentPriceView);
        blokCurrentPriceView = findViewById(R.id.blokCurrentPriceView);
        textViews.add(blokCurrentPriceView);
        boaCurrentPriceView = findViewById(R.id.boaCurrentPriceView);
        textViews.add(boaCurrentPriceView);
        chessCurrentPriceView = findViewById(R.id.chessCurrentPriceView);
        textViews.add(chessCurrentPriceView);
        cosCurrentPriceView = findViewById(R.id.cosCurrentPriceView);
        textViews.add(cosCurrentPriceView);
        hardCurrentPriceView = findViewById(R.id.hardCurrentPriceView);
        textViews.add(hardCurrentPriceView);
        iotxCurrentPriceView = findViewById(R.id.iotxCurrentPriceView);
        textViews.add(iotxCurrentPriceView);
        manaCurrentPriceView = findViewById(R.id.manaCurrentPriceView);
        textViews.add(manaCurrentPriceView);
        mblACurrentPriceView = findViewById(R.id.mblACurrentPriceView);
        textViews.add(mblACurrentPriceView);
        mboxCurrentPriceView = findViewById(R.id.mboxCurrentPriceView);
        textViews.add(mboxCurrentPriceView);
        nwcCurrentPriceView = findViewById(R.id.nwcCurrentPriceView);
        textViews.add(nwcCurrentPriceView);
        onxCurrentPriceView = findViewById(R.id.onxCurrentPriceView);
        textViews.add(onxCurrentPriceView);
        racaCurrentPriceView = findViewById(R.id.racaCurrentPriceView);
        textViews.add(racaCurrentPriceView);
        tlmCurrentPriceView = findViewById(R.id.tlmCurrentPriceView);
        textViews.add(tlmCurrentPriceView);
        zenCurrentPriceView = findViewById(R.id.zenCurrentPriceView);
        textViews.add(zenCurrentPriceView);




        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }

    private void loadData(){
        CryptoService cryptoService = retrofit.create(CryptoService.class);
        Call<List<CurrencyRequestDto>> call = cryptoService.getData();
        call.enqueue(new Callback<List<CurrencyRequestDto>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<CurrencyRequestDto>> call, Response<List<CurrencyRequestDto>> response) {
                if(response.isSuccessful()){
                    List<CurrencyRequestDto> responseList = response.body();
                    currencyRequestDtos = responseList;
                    Collections.sort(currencyRequestDtos, new Comparator<CurrencyRequestDto>() {
                        public int compare(CurrencyRequestDto v1, CurrencyRequestDto v2) {
                            return v1.getId().compareTo(v2.getId());
                        }
                    });
                    for (CurrencyRequestDto currencyRequestDto : currencyRequestDtos){
                        Log.d(" id: ", currencyRequestDto.getId() +" name: "
                                + currencyRequestDto.getName()  + " price: " + currencyRequestDto.getPrice());
                    }
                    int i = 0;
                    for(TextView textView : textViews){
                        if(!TextUtils.isEmpty(currencyRequestDtos.get(i).getPrice())){
                            textView.setText(currencyRequestDtos.get(i).getPrice().substring(0,8));
                            currenntCurrencyPrices.add(currencyRequestDtos.get(i).getPrice().substring(0,8));
                        }
                        else {
                            textView.setText("No Response");
                            currenntCurrencyPrices.add("No Response");
                        }
                        i++;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CurrencyRequestDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void goSecondActivity(View view){
        Intent intent = new Intent(getApplicationContext(), AnalizActivity.class);
        intent.putStringArrayListExtra("durakİsmi", currenntCurrencyPrices);
        startActivity(intent);
    }
}