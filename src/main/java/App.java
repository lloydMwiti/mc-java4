import com.google.gson.Gson;
import dao.Person;
import dao.Ranger;
import models.ListAll;
import models.RangerModel;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[]args){
        staticFileLocation("/public");


        Sql2oFoodtypeDao foodtypeDao;
        Sql2oRestaurantDao restaurantDao;
        Sql2oReviewDao reviewDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/animals";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "chowder");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();

        //CREATE
        post("/department/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentdao.add(department);
            res.status(201);;
            return gson.toJson(department);
        });

        //READ
        get("/department", "application/json", (req, res) -> {
            return gson.toJson(departmentdao.getAll());
        });

        get("/department/:id", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.findById(departmentId));
        });

        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });

    }
}
