package com.example.cryptocurrency.Dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CurrencyRequestDto {

    @SerializedName("asset_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("price_usd")
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
