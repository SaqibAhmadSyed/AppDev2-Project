package com.example.horizon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("api/games")
    Call<List<Game>> getGames();
}
