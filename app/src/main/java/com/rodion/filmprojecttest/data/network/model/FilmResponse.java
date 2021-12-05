package com.rodion.filmprojecttest.data.network.model;

import java.util.List;

public class FilmResponse {
    public int page;
    public List<Film> results;
    public int total_results;
    public int total_pages;
}
