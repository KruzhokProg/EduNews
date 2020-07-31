package model;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

public class NewsDataSource2 extends PositionalDataSource<News> {


    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<News> callback) {
        // some changes
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<News> callback) {

    }
}
