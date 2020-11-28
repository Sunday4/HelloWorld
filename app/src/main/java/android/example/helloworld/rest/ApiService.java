package android.example.helloworld.rest;



import android.example.helloworld.Model.RootModelMovie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("movie/popular?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US&page=1")
    Call<RootModelMovie>getData();


}
