package dao;

import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Userssql implements UsersDao {
    private Sql2o sql2o;

    public Userssql(Sql2o sql2o){
        this.sql2o=sql2o;
    }
    @Override
    public List<Users> all() {

        try(Connection con =sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(Users.class);
        }
    }

    @Override
    public void add(Users users) {
        String sql="INSERT INTO users (name,email,badge) VALUES (:name,:email,:badge)";
        try(Connection con=this.sql2o.open()){
            con.createQuery(sql,true)
                    .bind(users)
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
