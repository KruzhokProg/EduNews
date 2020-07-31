package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import java.util.List;

import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDataSource2 extends PositionalDataSource<News> {

    private ApiInterface apiService;
    private Application application;

    public static int PAGE_SIZE = 10;
    public static int LOAD_COUNT = 100;

    public NewsDataSource2(ApiInterface apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<News> callback) {

        int totalCount = LOAD_COUNT;
        int position = computeInitialLoadPosition(params, totalCount);
        int loadSize = computeInitialLoadSize(params, position, totalCount);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<News>> call = apiService.getNewsByCount(totalCount);
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> data = response.body();
                callback.onResult(data, position, data.size());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<News> callback) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<News>> call = apiService.getNews();
    }
}
