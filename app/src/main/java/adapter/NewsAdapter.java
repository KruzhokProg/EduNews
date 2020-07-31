package adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edunews.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

import model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    List<News> data;

    public NewsAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
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
//        Document doc = Jsoup.parse(data.get(position).getDescription().getRendered());
//        Elements pElements = doc.select("p");
        String[] date = data.get(position).getDate().split("T");
        holder.tvTitle.setText(data.get(position).getTitle().getRendered());
        holder.tvDate.setText(date[0]);
        holder.tvDesc.setText(Html.fromHtml(data.get(position).getDescription().getRendered(), Html.FROM_HTML_MODE_COMPACT));
    }

    @Override
    public int getItemCount() {
        return data.size();
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
