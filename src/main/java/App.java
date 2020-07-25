import com.google.gson.Gson;
import dao.Departmentsql;
import dao.Newssql;
import dao.Userssql;
import models.Department;
import models.Users;
import org.sql2o.Sql2o;

import java.sql.Connection;

import static spark.Spark.*;

public class App {

    public static void main(String[]args){
        staticFileLocation("/public");
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/animals";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "chowder");
        Departmentsql departmentdao=new Departmentsql(sql2o);
        Userssql usersdao=new Userssql(sql2o);
        Newssql newsdao=new Newssql(sql2o);
        conn = (Connection) sql2o.open();

        //CREATE DEP
        post("/department/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentdao.add(department);
            res.status(201);;
            return gson.toJson(department);
        });

        //READ DEP
        get("/department", "application/json", (req, res) -> {
            return gson.toJson(departmentdao.all());
        });
        //CREATE DEP
        post("/users/new", "application/json", (req, res) -> {
            Users users = gson.fromJson(req.body(), Users.class);
            usersdao.add(users);
            res.status(201);;
            return gson.toJson(users);
        });

        //READ DEP
        get("/users", "application/json", (req, res) -> {
            return gson.toJson(usersdao.all());
        });


        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });

    }
}
