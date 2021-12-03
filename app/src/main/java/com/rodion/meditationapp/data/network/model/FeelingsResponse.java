package com.rodion.meditationapp.data.network.model;

import java.util.List;

public class FeelingsResponse {
    public boolean success;
    public List<Feeling> data;

    public FeelingsResponse(boolean success, List<Feeling> data) {
        this.success = success;
        this.data = data;
    }
}
