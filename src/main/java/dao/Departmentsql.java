package dao;

import models.Department;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Departmentsql implements DepartmentDao {
    private Sql2o sql2o;

    public Departmentsql(Sql2o sql2o){
        this.sql2o=sql2o;
    }
    @Override
    public List<Department> all() {
        try(Connection con =sql2o.open()){
            return con.createQuery("SELECT * FROM department")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public void add(Department department) {
        String sql="INSERT INTO department (name) VALUES (:name)";
        try(Connection con=this.sql2o.open()){
            con.createQuery(sql,true)
                    .bind(department)
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
