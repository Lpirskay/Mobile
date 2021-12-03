package com.rodion.meditationapp.data.network.model;

import java.util.List;

public class QuotesResponse {
    public List<Quote> data;
    public String success;


    public QuotesResponse(List<Quote> quotesData, String success) {
        this.data = quotesData;
        this.success = success;
    }
}
