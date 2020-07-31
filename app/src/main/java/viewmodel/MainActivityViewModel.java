package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import model.News;
import model.NewsDataSource;
import model.NewsDataSourceFactory;
import model.NewsRepository;
import rest.ApiClient;
import rest.ApiInterface;

public class MainActivityViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    LiveData<NewsDataSource> newsDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<News>> newsPagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        NewsDataSourceFactory factory = new NewsDataSourceFactory(apiService, application);
        newsDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                                   .setEnablePlaceholders(false)
                                   .setInitialLoadSizeHint(30)
                                   .setPageSize(10)
                                   .setPrefetchDistance(5)
                                   .build();

//                .setInitialLoadSizeHint(5)
//                .setPageSize(10)
//                .setPrefetchDistance(4)
//                .build();

        executor = Executors.newFixedThreadPool(5);

        newsPagedList = (new LivePagedListBuilder<Long, News>(factory, config)).setFetchExecutor(executor).build();
    }

    public LiveData<List<News>> getAllNews(){
        return newsRepository.getMutableLiveData();
    }

    public LiveData<PagedList<News>> getNewsPagedList() {
        return newsPagedList;
    }
}
