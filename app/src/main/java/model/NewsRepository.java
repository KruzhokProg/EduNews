package model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private ArrayList<News> news = new ArrayList<>();
    private MutableLiveData<List<News>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public NewsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<News>> getMutableLiveData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<News>> call = apiService.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> newsResponse = response.body();
                if(newsResponse != null){
                    mutableLiveData.setValue(newsResponse);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

}
