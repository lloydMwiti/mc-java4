package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> all();
    void add();
    void find(int id);

}
