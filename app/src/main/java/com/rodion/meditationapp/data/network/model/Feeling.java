package com.rodion.meditationapp.data.network.model;

public class Feeling implements Comparable<Feeling> {
    public int id;
    public String title;
    public String image;
    public int position;

    public Feeling(int id, String title, String image, int position) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.position = position;
    }

    @Override
    public int compareTo(Feeling feeling) {
        return position-feeling.position;
    }
}
