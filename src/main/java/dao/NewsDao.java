package dao;

import models.Department;
import models.News;

import java.util.List;

public interface NewsDao {
    List<News> all();
    void add(News news);
    void find(int id);

}
