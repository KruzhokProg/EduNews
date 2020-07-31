package rest;

import java.util.List;

import model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("posts/?categories=12&per_page=100")
    Call<List<News>> getNews();
    @GET("posts/?categories=12")
    Call<List<News>> getNewsByCount(@Query("per_page") int count);
    @GET("posts/?categories=12")
    Call<List<News>> getNewsWithPging(@Query("per_page") long page);
}
