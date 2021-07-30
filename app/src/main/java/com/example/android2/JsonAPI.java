package com.example.android2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonAPI {
@GET("posts")
    //@GET("posts")
    Call<List<Posts>> getPosts();


}
