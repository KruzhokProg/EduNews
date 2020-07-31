package com.example.edunews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import adapter.NewsAdapter;
import adapter.NewsPagedListAdapter;
import helpers.GlideImageGetter;
import model.News;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

//    ApiInterface apiService;

    private PagedList<News> news;
    private RecyclerView rv;
    private NewsPagedListAdapter newsAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);

//        mainActivityViewModel = new ViewModelProviders.of(this).get(MainActivityViewModel.class);
//        mainActivityViewModel = new ViewModelProvider(MainActivityViewModel.class);
        try {
            mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
//            mainActivityViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainActivityViewModel.class);
            mainActivityViewModel.getNewsPagedList().observe(this, new Observer<PagedList<News>>() {
                @Override
                public void onChanged(PagedList<News> newsFromLiveData) {
                    news = newsFromLiveData;
                    newsAdapter = new NewsPagedListAdapter(getApplicationContext());
                    newsAdapter.submitList(news);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                }
            });
        }
        catch (Exception ex){
            Log.d("ERROR!!!", ex.getMessage());
        }
    }
}


//        apiService = ApiClient.getClient().create(ApiInterface.class);
//        Call<List<News>> call = apiService.getNews();
//        call.enqueue(new Callback<List<News>>() {
//@Override
//public void onResponse(Call<List<News>> call, Response<List<News>> response) {
//        List<News> data = response.body();
//        NewsAdapter adapter = new NewsAdapter(getApplicationContext(), data);
//        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        rv.setAdapter(adapter);
//
//        }
//
//@Override
//public void onFailure(Call<List<News>> call, Throwable t) {
//
//        }
//        });
