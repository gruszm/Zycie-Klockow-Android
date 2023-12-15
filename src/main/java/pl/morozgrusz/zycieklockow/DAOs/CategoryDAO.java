package pl.morozgrusz.zycieklockow.DAOs;

import pl.morozgrusz.zycieklockow.entities.Category;

import java.util.List;

public interface CategoryDAO
{
    List<Category> findAll();
    Category findById(int id);
}
