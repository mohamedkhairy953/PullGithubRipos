package com.example.moham.pullgithubripos;

import com.example.moham.pullgithubripos.POJOs.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by moham on 8/19/2017.
 */

public interface GithubService {
    String ENDPOINT = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Repo>> reposForUser (@Path("user") String user,@Query("page") int num,@Query("per_page") int p);
}
