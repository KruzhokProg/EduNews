package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDataSource extends PageKeyedDataSource<Long, News> {

    private ApiInterface apiService;
    private Application application;

    public NewsDataSource(ApiInterface apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, News> callback) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<News>> call = apiService.getNewsWithPging(1);
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> news = response.body();
                callback.onResult(news, null, (long)2);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, News> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, News> callback) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<News>> call = apiService.getNewsWithPging(params.key);
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> news = response.body();
                if (news != null){
                    callback.onResult(news, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}
