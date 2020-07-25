package dao;

import models.Department;
import models.Users;

import java.util.List;

public interface UsersDao {
    List<Users> all();
    void add(String name,String email,String depId);
    void find(int id);

}
