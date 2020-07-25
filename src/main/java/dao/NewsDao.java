package dao;

import models.Department;
import models.News;

import java.util.List;

public interface NewsDao {
    List<News> all();
    void add(String news,String department);
    void find(int id);

}
