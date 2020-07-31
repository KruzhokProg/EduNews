package adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edunews.R;

import model.News;

public class NewsPagedListAdapter extends PagedListAdapter<News, NewsPagedListAdapter.NewsViewHolder> {

    private Context context;

    public NewsPagedListAdapter(Context context){
        super(News.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = getItem(position);
        String[] date = news.getDate().split("T");
        holder.tvTitle.setText(news.getTitle().getRendered());
        holder.tvDate.setText(date[0]);
        holder.tvDesc.setText(Html.fromHtml(news.getDescription().getRendered(), Html.FROM_HTML_MODE_COMPACT));
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvDate, tvDesc;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
