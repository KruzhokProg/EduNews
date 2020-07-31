package model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class News {
    private int id;
    private String date;
    @SerializedName("title")
    private Title title;
    private String link;
    @SerializedName("excerpt")
    private Description description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public News(String date, Title title, String link, Description description) {
        this.date = date;
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public static final DiffUtil.ItemCallback<News> CALLBACK = new DiffUtil.ItemCallback<News>() {
        @Override
        public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return true;
        }
    };
}
