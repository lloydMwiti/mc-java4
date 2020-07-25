package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> all();
    void add(String name);
    void find(int id);

}
