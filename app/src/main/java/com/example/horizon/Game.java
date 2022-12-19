package com.example.horizon;

import java.util.Random;

public class Game {

    private String title;
    private String short_description;
    private String genre;
    private String publisher;
    private String release_date;
    private String thumbnail;
    private double price;


    public void setPrice(double price) {
        this.price = price;
    }

    public Game(){

    }


    public Game(String title, String short_description, String genre, String publisher, String release_date, String thumbnail, double price) {
        this.title = title;
        this.short_description = short_description;
        this.genre = genre;
        this.publisher = publisher;
        this.release_date = release_date;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public Game(String title, String short_description, String genre, String publisher, String release_date, String thumbnail) {


        this.title = title;
        this.short_description = short_description;
        this.genre = genre;
        this.publisher = publisher;
        this.release_date = release_date;
        this.thumbnail = thumbnail;
        Random rand = new Random();

        double randomDouble = 50 + (60 - 50) * rand.nextDouble();
        String formattedDouble = String.format("%.2f", randomDouble);
        double result = Double.parseDouble(formattedDouble);

        this.price = result;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public String getThumbnail(){
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", short_description='" + short_description + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
