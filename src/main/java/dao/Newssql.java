package dao;

import models.Department;
import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Newssql implements NewsDao {
    private Sql2o sql2o;

    public Newssql(Sql2o sql2o){
        this.sql2o=sql2o;
    }
    @Override
    public List<News> all() {
        try(Connection con =sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void add(News news) {
        String sql="INSERT INTO news (news,departmennt) VALUES (:news,:department)";
        try(Connection con=this.sql2o.open()){
            con.createQuery(sql,true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
        }catch(Sql2oException e){
            System.out.println("failed ,check if you have the correct compiles in the build.gradle and have a postgres db ->see readme.md");
            System.out.println(e);
        }
    }

    @Override
    public void find(int id) {

    }
}
