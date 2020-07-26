import com.google.gson.Gson;
import dao.Departmentsql;
import dao.Newssql;
import dao.Userssql;
import models.Department;
import models.News;
import models.Users;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            res.status(200);
            return gson.toJson(departmentdao.all());
        });

        //CREATE USR
        post("/users/new", "application/json", (req, res) -> {
            Users users = gson.fromJson(req.body(), Users.class);
            usersdao.add(users);
            res.status(201);;
            return gson.toJson(users);
        });

        //READ USR
        get("/users", "application/json", (req, res) -> {
            res.status(200);
            return gson.toJson(usersdao.all());
        });

        //CREATE NEWS
        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsdao.add(news);
            res.status(201);;
            return gson.toJson(news);
        });

        //READ NEWS
        get("/news", "application/json", (req, res) -> {
            return gson.toJson(newsdao.all());
        });


            //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

    }
}
