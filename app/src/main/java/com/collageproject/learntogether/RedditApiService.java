package com.collageproject.learntogether;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RedditApiService {
    @GET("/r/technews.json")
    Call<RedditResponse> getTechNews(@Header("Authorization") String accessToken);
}
