package com.example.nbateamsactivity.api;



import com.example.nbateamsactivity.generated.model.TeamListDatum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class RetrofitInterface {

    public interface UserLoginClient {
        @GET("getTeam/list")
        Call<List<TeamListDatum>> listRepos();
    }



}
