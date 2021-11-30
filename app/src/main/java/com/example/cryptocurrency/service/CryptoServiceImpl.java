package com.example.cryptocurrency.service;

import com.example.cryptocurrency.Dtos.CurrencyRequestDto;

import java.util.List;

import retrofit2.Call;

public class CryptoServiceImpl implements CryptoService {
    @Override
    public Call<List<CurrencyRequestDto>> getData() {
        return null;
    }
}
