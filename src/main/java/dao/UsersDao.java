package dao;

import models.Department;
import models.Users;

import java.util.List;

public interface UsersDao {
    List<Users> all();
    void add(Users users);
    void find(int id);

}
