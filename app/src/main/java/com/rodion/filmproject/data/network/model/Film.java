package com.rodion.filmproject.data.network.model;

import java.util.List;

public class Film {
    public int id;
    public String poster_path;
    public boolean adult;
    public String overview;
    public String release_date;
    public List<Integer> genre_ids;
    public String original_title;
    public String original_language;
    public String title;
    public String backdrop_path;
    public float popularity;
    public int vote_count;
    public boolean video;
    public float vote_average;
}
