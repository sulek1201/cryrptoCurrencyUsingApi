package com.example.cryptocurrency.service;

import com.example.cryptocurrency.Dtos.CurrencyRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoService {

    //ALICE,ATLAS,BLOK,BOA,CHESS,COS,HARD,IOTX,MANA,MBL,MBOX,NWC,ONX,RACA,TLM,ZEN
    @GET("assets/ALICE,ATLAS,BLOK,BOA,CHESS,COS,HARD,IOTX,MANA,MBL,MBOX,NWC,ONX,RACA,TLM,ZEN?apikey=832CF580-7DE4-4D9D-9A4B-2BED1ACE2BB3")
    Call<List<CurrencyRequestDto>> getData();

}
