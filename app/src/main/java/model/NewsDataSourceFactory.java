package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import rest.ApiInterface;

public class NewsDataSourceFactory extends DataSource.Factory {

    private NewsDataSource newsDataSource;
    private ApiInterface apiService;
    private Application application;
    private MutableLiveData<NewsDataSource> mutableLiveData;

    public NewsDataSourceFactory(ApiInterface apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        newsDataSource = new NewsDataSource(apiService, application);
        mutableLiveData.postValue(newsDataSource);
        return newsDataSource;
    }

    public MutableLiveData<NewsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
