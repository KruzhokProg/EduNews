package model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class NewsData {
    @PrimaryKey
    int id;
}
